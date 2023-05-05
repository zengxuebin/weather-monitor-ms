package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 预警信息
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:05
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @TableField(exist = false)
    private AlertRule alertRule;
    /**
     * 预警级别
     */
    private String alertLevel;
    /**
     * 关联影响区域
     */
    @TableField(exist = false)
    private WeatherStation area;
    /**
     * 预警开始时间
     */
    private Date startTime;
    /**
     * 预警结束时间
     */
    private Date endTime;
    /**
     * 预警发布时间
     */
    private Date alertTime;
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
