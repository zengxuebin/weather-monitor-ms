package com.ecjtu.common.domain.entiry;

import com.ecjtu.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 用户对象 sys_user
 * @Author: ZengXueBin
 * @Date: 2023/3/24 19:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 部门id
     */
    private String deptId;
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
     * 删除标志(0存在 2删除)
     */
    private String delFlag;
    /**
     * 最后登陆ip
     */
    private String loginIp;
    /**
     * 角色对象
     */
    private List<SysRole> roles;
}
