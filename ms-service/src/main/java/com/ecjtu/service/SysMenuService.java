package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.SysMenu;

import java.util.Set;

/**
 * @Description: 菜单 业务层
 * @Author: ZengXueBin
 * @Date: 2023/4/2 20:38
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询菜单权限
     * @param userId 用户ID
     * @return 菜单列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    Set<String> selectMenuPermsByRoleId(Long roleId);
}
