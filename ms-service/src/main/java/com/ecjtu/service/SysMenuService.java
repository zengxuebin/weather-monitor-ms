package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.VO.RouterVO;
import com.ecjtu.domain.entity.SysMenu;

import java.util.List;
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

    /**
     * 根据用户id查询菜单
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端所需要的菜单
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVO> buildMenus(List<SysMenu> menus);

    /**
     * 查询系统菜单列表
     * @param menu 菜单信息
     * @param userId 角色id
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu, Long userId);
}
