package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.utils.RedisCache;
import com.ecjtu.common.utils.RedisKeyUtil;
import com.ecjtu.domain.entity.SysDictData;
import com.ecjtu.mapper.SysDictDataMapper;
import com.ecjtu.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 字典 业务处理层
 * @Author: ZengXueBin
 * @Date: 2023/4/8 08:41
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Autowired
    private SysDictDataMapper dictDataMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 新增保存字典数据信息
     * @param dict 字典数据
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDictData(SysDictData dict) {
        int row = dictDataMapper.insert(dict);
        if (row > 0) {
            // 缓存在redis中
            setRedis(dict);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     * @param dict 字典数据
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDictData(SysDictData dict) {
        int row = dictDataMapper.updateById(dict);
        if (row > 0) {
            // 缓存在redis中
            setRedis(dict);
        }
        return row;
    }

    /**
     * 批量删除字典数据信息
     * @param dictCodeList 需要删除的字典id
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDictByIds(List<Long> dictCodeList) {
        int row = 0;
        for (Long dictCode : dictCodeList) {
            SysDictData dict = dictDataMapper.selectById(dictCode);
            row += dictDataMapper.deleteById(dictCode);
            // 重新缓存在redis中 保证数据的一致性
            setRedis(dict);
        }
        return row;
    }

    /**
     * 将字典存入redis
     * @param dict 字典
     */
    private void setRedis(SysDictData dict) {
        LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictData::getDictType, dict.getDictType());
        List<SysDictData> dataList = dictDataMapper.selectList(wrapper);
        redisCache.setCacheObject(RedisKeyUtil.getDictKey(dict.getDictType()), dataList);
    }

}
