package com.ecjtu.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.constant.UserConstants;
import com.ecjtu.common.exception.CustomException;
import com.ecjtu.common.utils.RedisCache;
import com.ecjtu.common.utils.RedisKeyUtil;
import com.ecjtu.domain.entity.SysDictData;
import com.ecjtu.domain.entity.SysDictType;
import com.ecjtu.mapper.SysDictDataMapper;
import com.ecjtu.mapper.SysDictTypeMapper;
import com.ecjtu.service.SysDictTypeService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 字典类型 业务处理层
 * @Author: ZengXueBin
 * @Date: 2023/4/8 08:46
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeMapper dictTypeMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 项目启动时初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        resetDictCache();
    }

    /**
     * 根据字典类型查询数据
     * @param dictType 字典类型
     * @return 字典数据集合
     */
    @Override
    public List<SysDictData> selectDictDataByDictType(String dictType) {
        JSONArray arrayCache = redisCache.getCacheObject(RedisKeyUtil.getDictKey(dictType));
        List<SysDictData> dataList = null;
        if (ObjectUtils.isNotEmpty(arrayCache)) {
            dataList = arrayCache.toList(SysDictData.class);
        }

        if (ObjectUtils.isNotEmpty(dataList)) {
            return dataList;
        }

        LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictData::getDictType, dictType);
        wrapper.eq(SysDictData::getStatus, "0");
        wrapper.orderByAsc(SysDictData::getOrderNum);
        dataList = dictDataMapper.selectList(wrapper);
        if (ObjectUtils.isNotEmpty(dataList)) {
            redisCache.setCacheObject(RedisKeyUtil.getDictKey(dictType), dataList);
            return dataList;
        }
        return null;
    }

    /**
     * 新增保存字典类型
     *
     * @param dictType 字典类型
     */
    @Transactional
    @Override
    public void insertDictType(SysDictType dictType) {
        int row = dictTypeMapper.insert(dictType);
        if (row > 0) {
            redisCache.setCacheObject(RedisKeyUtil.getDictKey(dictType.getDictType()), null);
        }
    }

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型
     */
    @Transactional
    @Override
    public void updateDictType(SysDictType dictType) {
        SysDictType oldDictType = dictTypeMapper.selectById(dictType.getDictId());
        // 修改DictData表中的type
        LambdaUpdateWrapper<SysDictData> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(SysDictData::getDictType, dictType.getDictType());
        wrapper.eq(SysDictData::getDictType, oldDictType.getDictType());
        dictDataMapper.update(null, wrapper);

        int row = dictTypeMapper.updateById(dictType);
        if (row > 0) {
            LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysDictData::getDictType, dictType.getDictType());
            List<SysDictData> dataList = dictDataMapper.selectList(queryWrapper);
            redisCache.setCacheObject(RedisKeyUtil.getDictKey(dictType.getDictType()), dataList);
        }
    }

    /**
     * 删除字典类型
     *
     * @param dictIds 类型id
     */
    @Override
    public void deleteDictTypeByIds(List<Long> dictIds) {
        for (Long dictId : dictIds) {
            SysDictType dictType = dictTypeMapper.selectById(dictId);

            // 判断字典表中是否有该类型
            LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysDictData::getDictType, dictType.getDictType());
            if (dictDataMapper.selectCount(wrapper) > 0) {
                throw new CustomException(dictType.getDictType() + "已分配，不能删除");
            }

            dictTypeMapper.deleteById(dictId);
            redisCache.deleteObject(dictType.getDictType());
        }
    }

    /**
     * 重置字段缓存数据
     */
    @Override
    public void resetDictCache() {
        // 清空字典缓存数据
        Collection<String> keys = redisCache.keys(RedisKeyUtil.getDictKey("*"));
        redisCache.deleteObject(keys);

        SysDictData dictData = new SysDictData();
        dictData.setStatus("0");
        LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictData::getStatus, "0");
        Map<String, List<SysDictData>> dictDataMap =
                dictDataMapper.selectList(wrapper).stream().collect(Collectors.groupingBy(SysDictData::getDictType));

        // 加载字典缓存数据
        for (Map.Entry<String, List<SysDictData>> entry : dictDataMap.entrySet()) {
            redisCache.setCacheObject(RedisKeyUtil.getDictKey(entry.getKey()), entry.getValue()
                    .stream().sorted(Comparator.comparing(SysDictData::getOrderNum))
                    .collect(Collectors.toList()));
        }
    }

    /**
     * 校验字典类型是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public boolean checkDictTypeUnique(SysDictType dictType) {
        long dictId = dictType.getDictId() == null ? -1L : dictType.getDictId();
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictType::getDictType, dictType.getDictType());

        SysDictType sysDictType = dictTypeMapper.selectOne(wrapper);

        if (sysDictType != null && sysDictType.getDictId() != dictId) {
            return UserConstants.NOT_UNIQUE;
        }

        return UserConstants.UNIQUE;
    }
}
