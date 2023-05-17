package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_alert_log")
public class AlertLog {

    /**
     * 预警记录日志ID
     */
    @TableId
    private Long alertLogId;
    /**
     * 预警信息
     */
    private Long alertId;
    /**
     * 处理人
     */
    private String handlerUser;
    /**
     * 处理时间
     */
    private Date handleTime;
    /**
     * 处理结果
     */
    private String handleResult;
}
