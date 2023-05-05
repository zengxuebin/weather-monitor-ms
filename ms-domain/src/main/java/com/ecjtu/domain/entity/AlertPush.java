package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 推送预警记录
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:39
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlertPush {

    /**
     * 推送记录ID
     */
    @TableId
    private Integer pushId;
    /**
     * 预警记录
     */
    @TableField(exist = false)
    private WeatherAlert alert;
    /**
     * 推送方式
     */
    private String pushMethod;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;

}
