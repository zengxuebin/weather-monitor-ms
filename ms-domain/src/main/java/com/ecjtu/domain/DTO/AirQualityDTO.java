package com.ecjtu.domain.DTO;

import lombok.Data;

/**
 * @Description: 空气质量
 * @Author: ZengXueBin
 * @Date: 2023/5/16 10:33
 */
@Data
public class AirQualityDTO {

    /**
     * pm2.5
     */
    private Float pm25;
    /**
     * pm10
     */
    private Float pm10;
    /**
     * 臭氧浓度
     */
    private Float o3;
    /**
     * 二氧化氮浓度
     */
    private Float no2;
    /**
     * 二氧化硫浓度
     */
    private Float so2;
    /**
     * 一氧化碳浓度
     */
    private Float co;
    /**
     * aqi
     */
    private Float aqi;
    /**
     * 描述
     */
    private String desc;
}
