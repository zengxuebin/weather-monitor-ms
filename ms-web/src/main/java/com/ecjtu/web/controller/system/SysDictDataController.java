package com.ecjtu.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.SysDictData;
import com.ecjtu.domain.model.SysDictDataQuery;
import com.ecjtu.service.SysDictDataService;
import com.ecjtu.service.SysDictTypeService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数据字典信息
 * @Author: ZengXueBin
 * @Date: 2023/4/8 08:37
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController {

    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    /**
     * 分页查询字典字段
     * @param query 查询字段
     * @return 结果集
     */
//    @PreAuthorize("@permissionService.hasPermission('system:dict:list')")
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<SysDictDataQuery> query) {
        LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
        SysDictDataQuery queryEntity = query.getEntity();
        if (ObjectUtils.isNotEmpty(queryEntity)) {
            if (StringUtils.isNotBlank(queryEntity.getDictType())) {
                wrapper.eq(SysDictData::getDictType, queryEntity.getDictType());
            }
            if (StringUtils.isNotBlank(queryEntity.getDictLabel())) {
                wrapper.eq(SysDictData::getDictLabel, queryEntity.getDictLabel());
            }
        }
        IPage<SysDictData> page = new Page<>(query.getPageNum(), query.getPageSize());
        wrapper.orderByAsc(SysDictData::getOrderNum);
        return ApiResult.success(dictDataService.page(page, wrapper));
    }

    /**
     * 获取字典键值列表
     * @param dictType 字典类型
     * @return 键值列表
     */
    @GetMapping("/list/{dictType}")
    public ApiResult getDictDataByDictType(@PathVariable String dictType) {
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictData::getDictType, dictType);
        List<SysDictData> dictDataList = dictDataService.list(queryWrapper);
        return ApiResult.success(dictDataList);
    }

    /**
     * 查询字典明细
     * @param dictCode 字典编码
     * @return 字典
     */
    @GetMapping("/detail/{dictCode}")
    public ApiResult getInfoByDictCode(@PathVariable String dictCode) {
        return ApiResult.success(dictDataService.getById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据
     * @param dictType 字典类型
     * @return 字典
     */
    @GetMapping("/type/{dictType}")
    public ApiResult getInfoListByDictType(@PathVariable String dictType) {
        List<SysDictData> dataList = dictTypeService.selectDictDataByDictType(dictType);
        if (ObjectUtils.isEmpty(dataList)) {
            dataList = new ArrayList<>();
        }
        return ApiResult.success(dataList);
    }

    /**
     * 新增字典
     * @param dict 字典
     * @return 结果
     */
    @PostMapping("/save")
    public ApiResult saveDict(@Validated @RequestBody SysDictData dict) {
        String username = SecurityUtil.getLoginUser().getUsername();
        dict.setCreateBy(username);
        int row = dictDataService.insertDictData(dict);

        return row > 0 ? ApiResult.success("创建成功") : ApiResult.error("创建失败");
    }

    /**
     * 修改字典
     * @param dict 字典
     * @return 结果
     */
    @PostMapping("/edit")
    public ApiResult editDict(@Validated @RequestBody SysDictData dict) {
        String username = SecurityUtil.getLoginUser().getUsername();
        dict.setUpdateBy(username);
        int row = dictDataService.updateDictData(dict);
        return row > 0 ? ApiResult.success("修改成功") : ApiResult.error("修改失败");
    }

    /**
     * 删除字典
     * @param dictCodeList 待删除字典id列表
     * @return 结果
     */
    @PostMapping("/del")
    public ApiResult removeDict(List<Long> dictCodeList) {
        int row = dictDataService.deleteDictByIds(dictCodeList);
        return row == dictCodeList.size() ? ApiResult.success("删除成功") : ApiResult.error("删除失败");
    }
}
