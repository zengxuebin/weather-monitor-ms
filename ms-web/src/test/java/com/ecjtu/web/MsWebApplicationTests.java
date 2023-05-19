package com.ecjtu.web;

import com.ecjtu.domain.model.Mail;
import com.ecjtu.service.AlertPushService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@EnableConfigurationProperties
class MsWebApplicationTests {


    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        Object o = restTemplate.getForObject(
                "https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/115.892151,28.676493/hourly?hourlysteps=24",
                String.class);
        System.out.println(o);
    }

    @Autowired
    private AlertPushService alertPushService;

    @Test
    public void testPwd() {
        Mail mail = new Mail();
        mail.setTos("zengxb0093@163.com");
        mail.setSubject("test mail");
        mail.setContent("this is a test mail");
        alertPushService.pushAlertByMail(mail);
    }
}
