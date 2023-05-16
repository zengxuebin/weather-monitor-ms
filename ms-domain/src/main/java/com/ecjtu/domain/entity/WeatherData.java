package com.ecjtu.domain.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 气象数据表
 * @Author: ZengXueBin
 * @Date: 2023/5/4 02:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_weather_data")
public class WeatherData {

    /**
     * 气象数据ID
     */
    @TableId
    private Long dataId;
    /**
     * 站点
     */
    private Long stationNo;
    /**
     * 数据采集时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dataCollectTime;
    /**
     * 天气描述
     */
    private String weather;
    /**
     * 温度
     */
    private Float temperature;
    /**
     * 湿度
     */
    private Float humidity;
    /**
     * 气压
     */
    private Float pressure;
    /**
     * 风速
     */
    private Float windSpeed;
    /**
     * 风向
     */
    private Integer windDirection;
    /**
     * 降水量
     */
    private Float precipitation;
    /**
     * 云量
     */
    private Float clouds;
    /**
     * 能见度
     */
    private Integer visibility;
    /**
     * 空气质量描述
     */
    private String airQualityDesc;
    /**
     * 空气质量指标数值
     */
    private Integer aqi;
    /**
     * pm2.5浓度
     */
    private Integer pm25;
    /**
     * pm10浓度
     */
    private Integer pm10;
    /**
     * 二氧化氮浓度
     */
    private Integer no2;
    /**
     * 二氧化硫
     */
    private Integer so2;
    /**
     * 臭氧
     */
    private Integer o3;
    /**
     * 一氧化氮
     */
    private Float co;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
    /**
     * 舒适度
     */
    @TableField(exist = false)
    private String comfort;

}
