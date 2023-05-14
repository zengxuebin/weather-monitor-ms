package com.ecjtu.web.service;

import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.service.SysMenuService;
import com.ecjtu.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 用户权限处理
 * @Author: ZengXueBin
 * @Date: 2023/4/2 11:56
 */
@Component
public class SysPermissionService {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;


    /**
     * 获得角色权限
     * @param sysUser 用户信息
     * @return 角色权限
     */
    public Set<String> getRolePermission(SysUser sysUser) {
        HashSet<String> roles = new HashSet<>();
        if (sysUser.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(sysUser.getUserId()));
        }
        return roles;
    }

    /**
     * 获得菜单权限
     * @param sysUser 用户
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser sysUser) {
        HashSet<String> roleMenus = new HashSet<>();
        if (sysUser.isAdmin()) {
            roleMenus.add("*:*");
        } else {
            roleMenus.addAll(menuService.selectMenuPermsByUserId(sysUser.getUserId()));
        }
        return roleMenus;
    }
}
