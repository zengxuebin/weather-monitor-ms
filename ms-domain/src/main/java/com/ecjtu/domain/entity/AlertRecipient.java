package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 预警接收
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:42
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlertRecipient {

    /**
     * 用户接收ID
     */
    @TableId
    private Long recipientId;
    /**
     * 推送记录
     */
    @TableField(exist = false)
    private AlertPush alertPush;
    /**
     * 用户
     */
    @TableField(exist = false)
    private SysUser user;
    /**
     * 是否已读
     */
    private String isRead;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
}
