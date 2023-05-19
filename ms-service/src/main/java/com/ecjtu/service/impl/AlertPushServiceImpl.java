package com.ecjtu.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.constant.AliSmsConstants;
import com.ecjtu.common.utils.AliSmsUtil;
import com.ecjtu.domain.entity.AlertPush;
import com.ecjtu.domain.model.Mail;
import com.ecjtu.mapper.AlertPushMapper;
import com.ecjtu.service.AlertPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警推送 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:08
 */
@Service
public class AlertPushServiceImpl extends ServiceImpl<AlertPushMapper, AlertPush> implements AlertPushService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 短信推送预警信息
     */
    public void pushAlertByAliSms() {
        try {
            Client client = AliSmsUtil.createClient(AliSmsConstants.ACCESS_KEY, AliSmsConstants.ACCESS_SECRET);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    // 短信签名-可在短信控制台中找到
                    .setSignName("your_value")
                    // 短信模板-可在短信控制台中找到
                    .setTemplateCode("your_value")
                    // 待发送手机号。支持以逗号分隔的形式进行批量调用
                    .setPhoneNumbers("your_value")
                    // 模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
                    .setTemplateParam("your_value");
            SendSmsResponse response = client.sendSms(sendSmsRequest);
            System.out.println(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 邮件推送预警信息
     */
    @Override
    public void pushAlertByMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(mail.getTos());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getContent());
        mailSender.send(mailMessage);
    }
}
