package com.ecjtu.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.constant.AliSmsConstants;
import com.ecjtu.common.utils.AliSmsUtil;
import com.ecjtu.domain.entity.AlertPush;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.domain.entity.WeatherAlert;
import com.ecjtu.domain.model.Mail;
import com.ecjtu.mapper.AlertPushMapper;
import com.ecjtu.mapper.SysUserMapper;
import com.ecjtu.mapper.WeatherAlertMapper;
import com.ecjtu.service.AlertPushService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 预警推送 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:08
 */
@Service
public class AlertPushServiceImpl extends ServiceImpl<AlertPushMapper, AlertPush> implements AlertPushService {

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private WeatherAlertMapper alertMapper;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 推送预警信息
     *
     * @param alertIds 预警信息ID
     * @return 结果
     */
    @Override
    public List<SysUser> pushAlert(List<Long> alertIds) {
        // 查询出所有普通用户
        List<SysUser> userList = userMapper.findUsersWithRole(2);
        // 查询出所有的预警信息
        LambdaQueryWrapper<WeatherAlert> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(WeatherAlert::getAlertId, alertIds);
        List<WeatherAlert> alertList = alertMapper.selectList(queryWrapper);

        for (WeatherAlert weatherAlert : alertList) {
            for (SysUser user : userList) {

                String phone = user.getPhone();
                pushAlertByAliSms(phone);
                // 邮箱推送
                String email = user.getEmail();
                Mail mail = new Mail();
                mail.setTos(email);
                mail.setSubject("气象智慧监测温馨提示");
                mail.setContent(weatherAlert.getAlertDesc());
                pushAlertByMail(mail);
            }
        }
        return userList;
    }

    /**
     * 短信推送预警信息
     */
    public void pushAlertByAliSms(String phone) {
        try {
            Client client = AliSmsUtil.createClient(AliSmsConstants.ACCESS_KEY, AliSmsConstants.ACCESS_SECRET);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    // 短信签名-可在短信控制台中找到
                    .setSignName(AliSmsConstants.SIGN_NAME)
                    // 短信模板-可在短信控制台中找到
                    .setTemplateCode(AliSmsConstants.TEMPLATE_CODE)
                    // 待发送手机号。支持以逗号分隔的形式进行批量调用
                    .setPhoneNumbers(phone);
                    // TODO 模板中的变量替换JSON串
                    //.setTemplateParam("");
            SendSmsResponse response = client.sendSms(sendSmsRequest);
            System.out.println(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 邮件推送预警信息
     */
    public void pushAlertByMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(mail.getTos());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getContent());
        mailSender.send(mailMessage);
    }
}
