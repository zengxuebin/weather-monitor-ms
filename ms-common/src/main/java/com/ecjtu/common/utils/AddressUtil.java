package com.ecjtu.common.utils;

import org.springframework.web.client.RestTemplate;


/**
 * @Description: 获取地址类
 * @Author: ZengXueBin
 * @Date: 2023/3/24 11:10
 */
public class AddressUtil {

    /**
     * ip地址查询
     */
    public static final String IP_URL = "https://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 获取ip地址信息
     */
    public static String getAddress() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(IP_URL, String.class);
        assert result != null;
        int prefixIndex = result.indexOf("{", result.indexOf("{") + 1);
        int suffixIndex = result.lastIndexOf("}", result.lastIndexOf("}") - 1);
        return result.substring(prefixIndex, suffixIndex + 1);
    }
}
