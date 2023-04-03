package com.ecjtu.web.security.context;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * @Description: 权限信息
 * @Author: ZengXueBin
 * @Date: 2023/3/31 14:34
 */
public class PermissionContextHolder {

    private static final String PERMISSION_CONTEXT = "permission_context";

    /**
     * 设置权限
     * @param permission 权限信息
     */
    public static void setContext(String permission) {
        RequestContextHolder.currentRequestAttributes().setAttribute(PERMISSION_CONTEXT,
                permission,
                RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 获得权限上下文
     * @return 权限上下文
     */
    public static String getContext() {
        return Objects.requireNonNull(RequestContextHolder.currentRequestAttributes().getAttribute(PERMISSION_CONTEXT,
                RequestAttributes.SCOPE_REQUEST)).toString();
    }
}
