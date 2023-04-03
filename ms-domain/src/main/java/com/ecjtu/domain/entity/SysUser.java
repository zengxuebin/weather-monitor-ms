package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ecjtu.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @Description: 用户对象 sys_user
 * @Author: ZengXueBin
 * @Date: 2023/3/24 19:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class SysUser extends BaseEntity {

    /**
     * 用户id
     */
    @TableId
    private Long userId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 账号状态
     */
    private String status;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
    /**
     * 最后登陆ip
     */
    private String loginIp;
    /**
     * 最后登陆时间
     */
    private Date loginTime;
    /**
     * 角色对象
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    private boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
