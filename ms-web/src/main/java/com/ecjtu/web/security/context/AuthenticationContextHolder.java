package com.ecjtu.web.security.context;

import org.springframework.security.core.Authentication;

/**
 * @Description: 身份认证信息
 * @Author: ZengXueBin
 * @Date: 2023/3/31 14:29
 */
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    /**
     * 获得身份认证信息
     * @return 身份认证信息
     */
    public static Authentication getContext() {
        return contextHolder.get();
    }

    /**
     * 设置身份认证信息
     * @param context 身份认证信息
     */
    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    /**
     * 移除身份认证信息
     */
    public static void clearContext() {
        contextHolder.remove();
    }
}
