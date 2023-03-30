package com.ecjtu.common.domain.entiry;

import com.ecjtu.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @Description: 角色表 sys_role
 * @Author: ZengXueBin
 * @Date: 2023/3/28 10:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {

    /**
     * 角色序号
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限
     *
     */
    private String rolePerms;
    /**
     * 角色状态 0正常 1停用
     */
    private String status;
    /**
     * 删除标志 0存在 2删除
     */
    private String delFlag;
    /**
     * 菜单组
     */
    private Long[] menuIds;
    /**
     * 部门组
     */
    private Long[] deptIds;
    /**
     * 角色菜单权限
     */
    private Set<String> permissions;
    /**
     * 显示顺序
     */
    private Integer orderNum;
}
