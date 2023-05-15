package com.ecjtu.domain.DTO;

import lombok.Data;

/**
 * @Description: 历史天气统计
 * @Author: ZengXueBin
 * @Date: 2023/5/15 09:36
 */
@Data
public class WeatherHistoryDTO {

    private Float maxTemp;
    private Float minTemp;
    private Float sumPrecipitation;
    private Float maxPrecipitation;
    private Float maxVisibility;
    private Float maxWindSpeed;

}
