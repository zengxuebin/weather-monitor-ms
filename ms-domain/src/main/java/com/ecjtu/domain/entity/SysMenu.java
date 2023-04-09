package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ecjtu.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: sys_menu 菜单权限表
 * @Author: ZengXueBin
 * @Date: 2023/3/28 09:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenu extends BaseEntity {
    /**
     * 菜单id
     */
    @TableId
    private Long menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单类型 0:content 1:menu 2:button
     */
    private String menuType;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 显示状态 0显示 1隐藏
     */
    private String visible;
    /**
     * 菜单状态 0正常 1停用
     */
    private String status;
    /**
     * 权限字符串
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();
}
