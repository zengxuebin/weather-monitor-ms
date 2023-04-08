package com.ecjtu.common.utils;

import com.ecjtu.common.constant.HttpStatus;

import java.util.HashMap;

/**
 * @Description: 返回前端结果集
 * @Author: ZengXueBin
 * @Date: 2023/3/23 15:46
 */
public class ApiResult extends HashMap<String, Object> {
    /**
     * 状态码
     */
    public static final String CODE = "code";
    /**
     * 状态消息
     */
    public static final String MSG = "msg";
    /**
     * 数据对象
     */
    public static final String DATA = "data";

    /**
     * 初始化result 使其表示一个空消息
     */
    private ApiResult() {
    }

    /**
     * 初始化result对象 不含data
     * @param code 状态码
     * @param msg 状态消息
     */
    public ApiResult(int code, String msg) {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * 初始化result对象 含data
     * @param code 状态码
     * @param msg 状态消息
     * @param data 数据对象
     */
    public ApiResult(int code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if (data != null) {
            super.put(DATA, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回消息
     * @param data 数据对象
     * @return 成功消息结果集
     */
    public static ApiResult success(String msg, Object data) {
        return new ApiResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息结果集
     */
    public static ApiResult success(Object data) {
        return new ApiResult(HttpStatus.SUCCESS, "操作成功", data);
    }



    /**
     * 返回成功消息 不含对象
     *
     * @param msg 消息内容
     * @return 成功消息结果集
     */
    public static ApiResult success(String msg) {
        return ApiResult.success(msg, null);
    }

    /**
     * 返回成功消息 不含消息和对象
     *
     * @return 成功消息结果集
     */
    public static ApiResult success() {
        return ApiResult.success("操作成功");
    }

    /**
     * 返回警告消息
     *
     * @param msg 消息内容
     * @param data 数据对象
     * @return 警告消息结果集
     */
    public static ApiResult warn(String msg, Object data) {
        return new ApiResult(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回警告消息 不含对象
     *
     * @param msg 消息内容
     * @return 警告消息结果集
     */
    public static ApiResult warn(String msg) {
        return ApiResult.warn(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 消息内容
     * @param data 数据对象
     * @return 错误消息结果集
     */
    public static ApiResult error(String msg, Object data) {
        return new ApiResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息 无data
     *
     * @param msg 消息内容
     * @return 错误消息结果集
     */
    public static ApiResult error(String msg) {
        return ApiResult.error(msg, null);
    }

    /**
     * 返回错误消息 无data 无msg
     *
     * @return 返回错误结果集
     */
    public static ApiResult error() {
        return ApiResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 消息内容
     * @return 错误消息
     */
    public static ApiResult error(int code, String msg) {
        return new ApiResult(code, msg, null);
    }

    /**
     * 链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public Object put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
