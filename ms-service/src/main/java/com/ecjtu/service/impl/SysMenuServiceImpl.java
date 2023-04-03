package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.SysMenu;
import com.ecjtu.mapper.SysMenuMapper;
import com.ecjtu.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 菜单 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/4/2 20:40
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> menus = menuMapper.selectMenuPermsByUserId(userId);
        HashSet<String> menuSet = new HashSet<>();
        for (String menu : menus) {
            if (StringUtils.isNotEmpty(menu)) {
                menuSet.addAll(Arrays.asList(menu.trim().split(",")));
            }
        }
        return menuSet;
    }

    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        List<String> menus = menuMapper.selectMenuPermsByRoleId(roleId);
        HashSet<String> menuSet = new HashSet<>();
        for (String menu : menus) {
            if (StringUtils.isNotEmpty(menu)) {
                menuSet.addAll(Arrays.asList(menu.trim().split(",")));
            }
        }
        return menuSet;
    }
}
