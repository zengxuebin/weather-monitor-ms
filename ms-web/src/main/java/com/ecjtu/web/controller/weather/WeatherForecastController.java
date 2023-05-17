package com.ecjtu.web.controller.weather;

import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.domain.DTO.ForecastWeatherDTO;
import com.ecjtu.web.service.ForeCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description: 预报
 * @Author: ZengXueBin
 * @Date: 2023/5/15 20:29
 */
@RestController
@RequestMapping("/forecast")
public class WeatherForecastController {

    @Autowired
    private ForeCastService foreCastService;

    /**
     * 获取天气预报
     * @param location 经纬度
     * @return 结果
     */
    @GetMapping("/{location}")
    public ApiResult getForecastWeather(@PathVariable String location) {
        RestTemplate restTemplate = new RestTemplate();
        String json  = "";
        json = restTemplate.getForObject(
                "https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/" + location + "/hourly?hourlysteps=192",
                String.class);
        List<ForecastWeatherDTO> forecastList = foreCastService.handleForecast(json);
        return ApiResult.success(forecastList);
    }
}
