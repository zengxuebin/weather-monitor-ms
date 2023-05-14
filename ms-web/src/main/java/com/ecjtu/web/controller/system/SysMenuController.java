package com.ecjtu.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.SysMenu;
import com.ecjtu.domain.model.SysMenuQuery;
import com.ecjtu.service.SysMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 菜单信息
 * @Author: ZengXueBin
 * @Date: 2023/4/9 14:23
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 分页查询菜单
     * @param query 查询条件
     * @return 菜单列表
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<SysMenuQuery> query) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        SysMenuQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getMenuName())) {
                queryWrapper.like(SysMenu::getMenuName, entity.getMenuName());
            }
            if (StringUtils.isNotBlank(entity.getMenuType())) {
                queryWrapper.eq(SysMenu::getMenuType, entity.getMenuType());
            }
            if (StringUtils.isNotBlank(entity.getVisible())) {
                queryWrapper.eq(SysMenu::getVisible, entity.getVisible());
            }
        }
        Page<SysMenu> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(menuService.page(page, queryWrapper));
    }

    /**
     * 获取菜单列表
     * @param menu 菜单
     * @return 菜单列表
     */
    @GetMapping("/list")
    public ApiResult queryList(SysMenu menu) {
        Long userId = SecurityUtil.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        if (ObjectUtils.isNotEmpty(menus)) {
            return ApiResult.success(menus);
        } else {
            return ApiResult.warn("您没有该权限，请联系系统管理员");
        }
    }

    /**
     * 根据菜单编号获取详情信息
     * @param menuId 菜单编号
     * @return 菜单详情
     */
    @GetMapping("/{menuId}")
    public ApiResult getMenuInfoById(@PathVariable Long menuId) {
        return ApiResult.success(menuService.getById(menuId));
    }


}
