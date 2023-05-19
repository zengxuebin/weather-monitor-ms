package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.AlertPush;
import com.ecjtu.domain.model.Mail;

/**
 * @Description: 预警推送 业务层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:02
 */
public interface AlertPushService extends IService<AlertPush> {

    /**
     * 通过邮件推送预警
     * @param mail 邮件详情
     */
    void pushAlertByMail(Mail mail);
}
