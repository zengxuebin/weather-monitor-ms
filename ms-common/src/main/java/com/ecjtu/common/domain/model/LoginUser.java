package com.ecjtu.common.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ecjtu.common.domain.entiry.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @Description: 登陆用户身份权限
 * @Author: ZengXueBin
 * @Date: 2023/3/24 18:52
 */
@NoArgsConstructor
@Data
public class LoginUser implements UserDetails, Serializable {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 登陆时间
     */
    private Long loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 登陆ip地址
     */
    private String ipAddress;
    /**
     * 登陆地点
     */
    private String loginLocation;

    /**
     * 用户信息
     */
    private SysUser user;
    /**
     * 权限列表
     */
    @JSONField(serialize = false)
    private Set<String> permissions;

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 账号是否未过期， 过期无法验证
     * @return true=未过期 false=过期
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否未解锁 锁定的用户无法进行身份验证
     * @return true=解锁 false=锁定
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户的凭据(密码)是否没过期 防止过期的凭证认证
     * @return true=未过期 false=过期
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return true=禁用 false=启用
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return false;
    }
}
