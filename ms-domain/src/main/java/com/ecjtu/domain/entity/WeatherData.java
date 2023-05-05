package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class WeatherData {

    /**
     * 气象数据ID
     */
    @TableId
    private Long dataId;
    /**
     * 站点
     */
    @TableField(exist = false)
    private WeatherStation station;
    /**
     * 数据采集时间
     */
    private Date dataCollectTime;
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
    private Integer windSpeed;
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
    private Integer clouds;
    /**
     * 能见度
     */
    private Integer visibility;
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
     * 臭氧浓度
     */
    private Float co;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;

}
