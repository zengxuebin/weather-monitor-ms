package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ecjtu.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 预警规则
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:08
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_alert_rule")
public class AlertRule extends BaseEntity {

    /**
     * 预警规则ID
     */
    @TableId
    private Long ruleId;
    /**
     * 预警规则名称
     */
    private String ruleName;
    /**
     * 预警监测指标
     */
    private String metric;
    /**
     * 比较运算符
     */
    private String operator;
    /**
     * 预警规则阈值
     */
    private Float threshold;
    /**
     * 预警级别
     */
    private String alertLevel;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 预警规则描述
     */
    private String ruleDesc;
    /**
     * 是否启用
     */
    private String alertStatus;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
}
