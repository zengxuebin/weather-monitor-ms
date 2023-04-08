package com.ecjtu.common.utils;

import com.ecjtu.common.constant.CacheConstants;

/**
 * @Description: redis key工具类
 * @Author: ZengXueBin
 * @Date: 2023/4/8 17:54
 */
public class RedisKeyUtil {
    private RedisKeyUtil() {
    }

    /**
     * 设置字典key
     * @param configKey key
     * @return 缓存key
     */
    public static String getDictKey(String configKey) {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }
}
