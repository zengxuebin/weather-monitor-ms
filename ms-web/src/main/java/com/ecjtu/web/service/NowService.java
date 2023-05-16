package com.ecjtu.web.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Description: 目前天气业务层
 * @Author: ZengXueBin
 * @Date: 2023/5/16 08:26
 */
@Service
public class NowService {

    /**
     * 处理未来两小时降雨数据
     * @param json
     * @return
     */
    public JSONArray handleNowWeather(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray precipitationArray = jsonObject.getJSONObject("result")
                .getJSONObject("minutely")
                .getJSONArray("precipitation_2h");
        String description = jsonObject.getJSONObject("result").getJSONObject("minutely").getString("description");

        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < precipitationArray.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("minutes", i + 1);
            obj.put("precipitation", precipitationArray.getFloatValue(i));
            resultArray.add(obj);
        }

        JSONArray finalResult = new JSONArray();
        finalResult.add(resultArray);
        finalResult.add(description);
        return finalResult;
    }
}
