package com.ecjtu.web.service;

import com.ecjtu.web.security.context.PermissionContextHolder;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.entity.SysRole;
import com.ecjtu.domain.model.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Description: 权限
 * @Author: ZengXueBin
 * @Date: 2023/3/31 13:58
 */
@Service
public class PermissionService {

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*";
    /**
     * 管理员权限标识
     */
    private static final String ADMIN_PERMISSION = "admin";
    /**
     * 分隔符
     */
    private static final String DELIMITER = ",";

    /**
     * 判断是否包含权限
     * @param permissions 权限列表
     * @param permission 权限字符串
     * @return true=有 false=无
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }

    /**
     * 验证用户是否具备某权限
     * @param permission 权限字符串
     * @return true=有 false=无
     */
    public boolean hasPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null || CollectionUtils.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        PermissionContextHolder.setContext(permission);
        return hasPermissions(loginUser.getPermissions(), permission);
    }

    /**
     * 验证用户是否不具备某权限 与hasPermission相反
     * @param permission 权限字符串
     * @return true=无 false=有
     */
    public boolean noPermission(String permission) {
        return !hasPermission(permission);
    }

    /**
     * 判断用户是否拥有某个角色
     * @param role 角色字符串
     * @return true=有 false=无
     */
    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role)) {
            return false;
        }

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null || CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
            return false;
        }
        for (SysRole sysRole : loginUser.getUser().getRoles()) {
            String roleKey = sysRole.getRoleKey();
            if (ADMIN_PERMISSION.equals(roleKey) || roleKey.equals(StringUtils.trim(role))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户是否不具备某角色 与hasRole相反
     * @param role 角色名称
     * @return true=无 false=有
     */
    public boolean noRole(String role) {
        return !hasRole(role);
    }
}
