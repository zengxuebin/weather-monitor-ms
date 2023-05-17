package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: 预警信息
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:05
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_weather_alert")
public class WeatherAlert {

    /**
     * 预警信息ID
     */
    @TableId
    private Long alertId;
    /**
     * 预警信息标题
     */
    private String alertTitle;
    /**
     * 触发预警的监测值
     */
    private Float triggerValue;
    /**
     * 预警详情信息
     */
    private String alertDesc;
    /**
     * 预警类型
     */
    private String alertType;
    /**
     * 关联预警规则
     */
    private Long alertRuleId;
    /**
     * 预警级别
     */
    private String alertLevel;
    /**
     * 关联影响区域
     */
    private Long alertAreaId;

    /**
     * 预警触发时间
     */
    private Date triggerTime;
    /**
     * 预警开始时间
     */
    private LocalDateTime startTime;
    /**
     * 预警结束时间
     */
    private LocalDateTime endTime;
    /**
     * 预警状态
     */
    private String alertStatus;
    /**
     * 预警来源
     */
    private String alertSource;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;

}
