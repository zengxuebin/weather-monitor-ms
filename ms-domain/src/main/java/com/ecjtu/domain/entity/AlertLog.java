package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 预警记录日志
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:48
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlertLog {

    /**
     * 预警记录日志ID
     */
    @TableId
    private Long alertLogId;
    /**
     * 预警信息
     */
    @TableField(exist = false)
    private WeatherAlert alert;
    /**
     * 预警记录生成时间
     */
    private Date alertTime;
    /**
     * 是否处理
     */
    private String isHandled;
    /**
     * 处理人
     */
    @TableField(exist = false)
    private SysUser handlerUser;
    /**
     * 处理时间
     */
    private Date handleTime;
    /**
     * 处理结果
     */
    private String handleResult;
}
