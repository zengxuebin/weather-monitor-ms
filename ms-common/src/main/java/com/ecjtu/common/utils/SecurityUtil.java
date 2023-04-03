package com.ecjtu.common.utils;

import com.ecjtu.common.constant.HttpStatus;
import com.ecjtu.common.exception.CustomException;
import com.ecjtu.domain.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: 安全工具类
 * @Author: ZengXueBin
 * @Date: 2023/3/31 14:05
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * 获取Authentication
     * @return 身份认证
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取登陆用户信息
     * @return 用户信息
     */
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "获取用户信息异常");
        }
    }

    /**
     * 获取用户id
     * @return 用户id
     */
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "获取用户ID异常");
        }
    }

    /**
     * 获取部门id
     * @return 部门id
     */
    public static Long getDeptId() {
        try {
            return getLoginUser().getDeptId();
        } catch (Exception e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "获取部门ID异常");
        }
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "获取用户账户异常");
        }
    }

    /**
     * 生成BCryptPasswordEncoder密码
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     * @param rawPassword 源密码
     * @param encodedPassword 加密密码
     * @return true=相同 false=不相同
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * @param userId 用户编号
     * @return true=管理员 false=非管理员
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
