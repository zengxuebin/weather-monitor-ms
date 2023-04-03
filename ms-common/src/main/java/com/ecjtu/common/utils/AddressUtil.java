package com.ecjtu.common.utils;

import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


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
    public static String getIpInfo(String key) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(IP_URL, String.class);
        assert result != null;
        int prefixIndex = result.indexOf("{", result.indexOf("{") + 1);
        int suffixIndex = result.lastIndexOf("}", result.lastIndexOf("}") - 1);
        String ipInfo = result.substring(prefixIndex, suffixIndex + 1);
        HashMap<?, ?> map = new HashMap<>();
        Gson gson = new Gson();
        map = gson.fromJson(ipInfo, map.getClass());
        return map.get(key).toString();
    }
}
