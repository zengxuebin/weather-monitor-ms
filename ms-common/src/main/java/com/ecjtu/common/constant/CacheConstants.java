package com.ecjtu.common.constant;

/**
 * @Description: 缓存key 常量
 * @Author: ZengXueBin
 * @Date: 2023/3/23 11:47
 */
public class CacheConstants {

    /**
     * 登陆用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_token";
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_code";
    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict";
    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit";
    /**
     * 登陆账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt";
}
