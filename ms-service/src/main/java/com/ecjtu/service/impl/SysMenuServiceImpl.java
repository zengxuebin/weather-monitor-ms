package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.VO.MetaVO;
import com.ecjtu.domain.VO.RouterVO;
import com.ecjtu.domain.entity.SysMenu;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.mapper.SysMenuMapper;
import com.ecjtu.service.SysMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 菜单 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/4/2 20:40
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户ID查询菜单权限
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> menus = menuMapper.selectMenuPermsByUserId(userId);
        HashSet<String> menuSet = new HashSet<>();
        for (String menu : menus) {
            if (StringUtils.isNotBlank(menu)) {
                menuSet.addAll(Arrays.asList(menu.trim().split(",")));
            }
        }
        return menuSet;
    }

    /**
     * 根据角色ID查询菜单权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        List<String> menus = menuMapper.selectMenuPermsByRoleId(roleId);
        HashSet<String> menuSet = new HashSet<>();
        for (String menu : menus) {
            if (StringUtils.isNotBlank(menu)) {
                menuSet.addAll(Arrays.asList(menu.trim().split(",")));
            }
        }
        return menuSet;
    }

    /**
     * 根据用户id查询菜单
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = null;
        if (SecurityUtil.isAdmin(userId)) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.select("distinct *").lambda()
                    .in(SysMenu::getMenuType, "0", "1")
                    .eq(SysMenu::getStatus, "0")
                    .orderByAsc(SysMenu::getParentId)
                    .orderByAsc(SysMenu::getOrderNum);
            menus = menuMapper.selectList(wrapper);
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildMenus(menus, 0);
    }

    /**
     * 构建前端所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVO> buildMenus(List<SysMenu> menus) {
        List<RouterVO> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVO router = new RouterVO();
            router.setName(menu.getMenuName());
            router.setPath(menu.getPath());
            router.setHidden("1".equals(menu.getVisible()));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon()));

            // 子菜单
            List<SysMenu> menuChildren = menu.getChildren();
            if (ObjectUtils.isNotEmpty(menuChildren) && "0".equals(menu.getMenuType())) {   // 一级菜单
                router.setChildren(buildMenus(menuChildren));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 角色id
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menuList = null;

        if (SysUser.isAdmin(userId)) {  // 管理员显示所有菜单信息
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(menu.getMenuName())) {
                wrapper.eq(SysMenu::getMenuName, menu.getMenuName());
            }
            if (StringUtils.isNotBlank(menu.getVisible())) {
                wrapper.eq(SysMenu::getVisible, menu.getVisible());
            }
            if (StringUtils.isNotBlank(menu.getStatus())) {
                wrapper.eq(SysMenu::getStatus, menu.getStatus());
            }

            wrapper.orderByAsc(SysMenu::getParentId)
                    .orderByAsc(SysMenu::getOrderNum);
            menuList = menuMapper.selectList(wrapper);
        }
        return menuList;
    }

    /**
     * 根据父节点id获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点
     * @return 子菜单
     */
    public List<SysMenu> getChildMenus(List<SysMenu> list, int parentId) {
        List<SysMenu> menuList = new ArrayList<>();
        for (SysMenu menu : list) {
            // 传入父节点id 遍历该父节点的所有子节点
            if (menu.getParentId() == parentId) {
                recursionMenu(list, menu);
                menuList.add(menu);
            }
        }
        return menuList;
    }


    /**
     * 递归列表
     *
     * @param list 分类列表
     * @param childMenu 子节点
     */
    private void recursionMenu(List<SysMenu> list, SysMenu childMenu) {
        // 获取子节点菜单列表
        List<SysMenu> childMenuList = getChildList(list, childMenu);
        childMenu.setChildren(childMenuList);

        for (SysMenu menu : childMenuList) {
            if (getChildList(list, menu).size() > 0) {
                recursionMenu(list, menu);
            }
        }
    }

    /**
     * 子节点菜单列表
     *
     * @param list 列表
     * @param childMenu 子节点
     * @return 自节点菜单
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu childMenu) {
        List<SysMenu> childMenuList = new ArrayList<>();
        for (SysMenu menu : list) {
            if (menu.getParentId().longValue() == childMenu.getMenuId().longValue()) {
                childMenuList.add(menu);
            }
        }
        return childMenuList;
    }

    /**
     * 是否为菜单内部跳转
     * @param menu 菜单
     * @return 结果
     */
    public boolean isMenuFrame(SysMenu menu) {
        return menu.getParentId().intValue() == 0 && "1".equals(menu.getMenuType());
    }
}
