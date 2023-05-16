package com.ecjtu.domain.DTO;

import lombok.Data;

/**
 * @Description: 实况天气
 * @Author: ZengXueBin
 * @Date: 2023/5/16 10:35
 */
@Data
public class WeatherNowDTO {

    private Float temperature;
    private AirQualityDTO airQuality;
}
