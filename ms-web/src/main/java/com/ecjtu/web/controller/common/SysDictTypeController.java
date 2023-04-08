package com.ecjtu.web.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.SysDictType;
import com.ecjtu.domain.model.SysDictTypeQuery;
import com.ecjtu.service.SysDictTypeService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 数据字典
 * @Author: ZengXueBin
 * @Date: 2023/4/8 14:15
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping("/list")
    public ApiResult queryPageList(@RequestBody PageInfo<SysDictTypeQuery> query) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        SysDictTypeQuery queryEntity = query.getEntity();
        if (ObjectUtils.isNotEmpty(queryEntity)) {
            if (StringUtils.isNotBlank(queryEntity.getDictName())) {
                wrapper.eq(SysDictType::getDictName, queryEntity.getDictName());
            }
            if (StringUtils.isNotBlank(queryEntity.getStatus())) {
                wrapper.eq(SysDictType::getStatus, queryEntity.getStatus());
            }
            if (StringUtils.isNotBlank(queryEntity.getDictType())) {
                wrapper.eq(SysDictType::getDictType, queryEntity.getDictType());
            }
            if (StringUtils.isNotBlank(queryEntity.getBeginTime())) {
                wrapper.ge(SysDictType::getCreateTime, queryEntity.getBeginTime());
            }
            if (StringUtils.isNotBlank(queryEntity.getEndTime())) {
                wrapper.le(SysDictType::getCreateTime, queryEntity.getEndTime());
            }
        }
        IPage<SysDictType> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(dictTypeService.page(page, wrapper));
    }

    /**
     * 查询字典类型明细
     * @param dictId 字典类型id
     * @return 字典类型数据
     */
    @GetMapping("/{dictId}")
    public ApiResult getInfoByDictId(@PathVariable Long dictId) {
        return ApiResult.success(dictTypeService.getById(dictId));
    }

    /**
     * 新增字典类型
     * @param dictType 字典类型
     * @return 结果
     */
    @PostMapping("/save")
    public ApiResult saveDictType(@Validated @RequestBody SysDictType dictType) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();

        // 判断字典类型是否唯一
        if (ObjectUtils.isNotEmpty(dictType)) {
            if (StringUtils.isNotBlank(dictType.getDictType())) {
                wrapper.eq(SysDictType::getDictType, dictType.getDictType());
            }
        }
        if (ObjectUtils.isNotEmpty(dictTypeService.getOne(wrapper))) {
            return ApiResult.error("新增字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }

        String username = SecurityUtil.getLoginUser().getUsername();
        dictType.setCreateBy(username);
        dictTypeService.insertDictType(dictType);
        return ApiResult.success("创建成功");
    }

    /**
     * 修改字典类型
     * @param dictType 字典类型
     * @return 结果
     */
    @PostMapping("/edit")
    public ApiResult editDictType(@Validated @RequestBody SysDictType dictType) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();

        // 判断字典类型是否唯一
        if (ObjectUtils.isNotEmpty(dictType)) {
            if (StringUtils.isNotBlank(dictType.getDictType())) {
                wrapper.eq(SysDictType::getDictType, dictType.getDictType());
            }
        }
        if (ObjectUtils.isNotEmpty(dictTypeService.getOne(wrapper))) {
            return ApiResult.error("修改字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }
        String username = SecurityUtil.getLoginUser().getUsername();
        dictType.setCreateBy(username);
        dictTypeService.updateDictType(dictType);
        return ApiResult.success("修改成功");
    }

    /**
     * 删除字典类型
     * @param dictIds 字典类型列表id
     * @return 结果
     */
    @PostMapping("/del")
    public ApiResult removeDictType(List<Long> dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return ApiResult.success("删除成功");
    }

    /**
     * 重置字典缓存
     * @return 成功
     */
    @PostMapping("/refreshCache")
    public ApiResult refreshCache() {
        dictTypeService.resetDictCache();
        return ApiResult.success();
    }
}
