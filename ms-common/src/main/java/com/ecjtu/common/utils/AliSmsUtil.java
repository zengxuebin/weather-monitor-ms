package com.ecjtu.common.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.ecjtu.common.constant.AliSmsConstants;

/**
 * @Description: 阿里短信发送工具类
 * @Author: ZengXueBin
 * @Date: 2023/5/20 00:01
 */
public class AliSmsUtil {

    private AliSmsUtil() {
    }

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId     密钥ID
     * @param accessKeySecret 密钥
     * @return client
     * @throws Exception e
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 必填 设置 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = AliSmsConstants.DO_MAIN;
        return new Client(config);
    }
}
