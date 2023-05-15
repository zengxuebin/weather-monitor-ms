package com.ecjtu.web.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ecjtu.domain.DTO.ForecastWeatherDTO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 预测天气业务处理
 * @Author: ZengXueBin
 * @Date: 2023/5/15 20:40
 */
@Service
public class ForeCastService {

    /**
     * 处理预测数据
     * @param json 预测原始数据
     * @return 处理结果
     */
    public List<ForecastWeatherDTO> handleForecast(String json) {
        JSONObject root = JSONObject.parseObject(json);

        JSONObject hourlyNode = root.getJSONObject("result").getJSONObject("hourly");
        ArrayList<ForecastWeatherDTO> forecastWeatherList = new ArrayList<>();

        JSONArray precipitationArray = hourlyNode.getJSONArray("precipitation");
        JSONArray temperatureArray = hourlyNode.getJSONArray("temperature");
        JSONArray humidityArray = hourlyNode.getJSONArray("humidity");
        JSONArray pressureArray = hourlyNode.getJSONArray("pressure");
        JSONArray visibilityArray = hourlyNode.getJSONArray("visibility");
        JSONArray cloudRateArray = hourlyNode.getJSONArray("cloudrate");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmXXX");

        for (int i = 0; i < precipitationArray.size(); i++) {
            String datetimeString = precipitationArray.getJSONObject(i).getString("datetime");
            Date datetime = null;
            try {
                datetime = dateFormat.parse(datetimeString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Float precipitation = precipitationArray.getJSONObject(i).getFloat("value");
            Float temperature = temperatureArray.getJSONObject(i).getFloat("value");
            Float humidity = humidityArray.getJSONObject(i).getFloat("value");
            Float pressure = pressureArray.getJSONObject(i).getFloat("value");
            Float visibility = visibilityArray.getJSONObject(i).getFloat("value");
            Float cloudRate = cloudRateArray.getJSONObject(i).getFloat("value");

            ForecastWeatherDTO forecastWeatherDTO = new ForecastWeatherDTO(datetime, precipitation, temperature,
                    pressure, visibility, cloudRate, humidity);
            forecastWeatherList.add(forecastWeatherDTO);
        }
        return forecastWeatherList;
    }
}
