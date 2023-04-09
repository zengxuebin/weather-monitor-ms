package com.ecjtu.web.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.SysDept;
import com.ecjtu.domain.model.SysDeptQuery;
import com.ecjtu.service.SysDeptService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 部门
 * @Author: ZengXueBin
 * @Date: 2023/4/9 09:36
 */
@RestController
@RequestMapping("/system/dept")
public class SysDpetController {

    @Autowired
    private SysDeptService deptService;

    @GetMapping("/list")
    public ApiResult queryPageList(@RequestBody PageInfo<SysDeptQuery> query) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        SysDeptQuery queryEntity = query.getEntity();
        if (ObjectUtils.isNotEmpty(queryEntity)) {
            if (StringUtils.isNotBlank(queryEntity.getLeader())) {
                wrapper.eq(SysDept::getLeader, queryEntity.getLeader());
            }
            if (StringUtils.isNotBlank(queryEntity.getDeptName())) {
                wrapper.eq(SysDept::getDeptName, queryEntity.getDeptName());
            }
            if (StringUtils.isNotBlank(queryEntity.getStatus())) {
                wrapper.eq(SysDept::getStatus, queryEntity.getStatus());
            }
        }

        IPage<SysDept> page = new Page<>(query.getPageNum(), query.getPageSize());
        wrapper.orderByAsc(SysDept::getOrderNum);
        return ApiResult.success(deptService.page(page, wrapper));
    }

    /**
     * 根据部门编号获取详情信息
     * @param deptId 部门编号
     * @return 详情信息
     */
    @GetMapping("/{deptId}")
    public ApiResult getDeptInfoById(@PathVariable Long deptId) {
        return ApiResult.success(deptService.getById(deptId));
    }

    /**
     * 新增部门
     * @param dept 部门
     * @return 结果
     */
    @PostMapping("/save")
    public ApiResult saveSysDept(@Validated @RequestBody SysDept dept) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();

        // 判断部门是否唯一
        if (!deptService.checkDeptNameUnique(dept)) {
            return ApiResult.warn("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }

        String username = SecurityUtil.getLoginUser().getUsername();
        dept.setCreateBy(username);
        boolean flag = deptService.save(dept);
        if (flag) {
            return ApiResult.success("保存成功");
        } else {
            return ApiResult.error("保存失败");
        }
    }

    /**
     * 修改部门
     * @param dept 部门
     * @return 结果
     */
    @PostMapping("/edit")
    public ApiResult editDept(@Validated @RequestBody SysDept dept) {
        Long deptId = dept.getDeptId();
        if (!deptService.checkDeptNameUnique(dept)) {
            return ApiResult.warn("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setUpdateBy(SecurityUtil.getLoginUser().getUsername());
        boolean flag = deptService.updateById(dept);
        if (flag) {
            return ApiResult.success("修改成功");
        } else {
            return ApiResult.error("修改失败");
        }
    }

    /**
     * 删除部门
     * @param deptIds 部门ids
     * @return 结果
     */
    @PostMapping("/del")
    public ApiResult removeDept(List<Long> deptIds) {
        boolean flag = deptService.removeByIds(deptIds);
        if (flag) {
            return ApiResult.success("删除成功");
        } else {
            return ApiResult.error("删除失败");
        }
    }
}
