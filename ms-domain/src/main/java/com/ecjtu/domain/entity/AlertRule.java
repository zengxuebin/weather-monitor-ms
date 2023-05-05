package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
     * 预警规则类型
     */
    private String ruleType;
    /**
     * 预警规则描述
     */
    private String ruleDesc;
    /**
     * 预警规则阈值
     */
    private Float thresholdValue;
    /**
     * 是否启用
     */
    private String alertStatus;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
}
