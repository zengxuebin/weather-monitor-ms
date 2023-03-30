package com.ecjtu.common.constant;

/**
 * @Description: 返回状态码
 * @Author: ZengXueBin
 * @Date: 2023/3/23 11:41
 */
public class HttpStatus {

    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;
    /**
     * 对象创建成功
     */
    public static final int CREATED = 201;
    /**
     * 请求已被接受
     */
    public static final int ACCEPTED = 202;
    /**
     * 参数列表错误(缺少 格式不匹配)
     */
    public static final int BAD_REQUEST = 400;
    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;
    /**
     * 访问受限 授权过期
     */
    public static final int FORBIDDEN = 403;
    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 405;
    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;
    /**
     * 系统警告信息
     */
    public static final int WARN = 601;
}
