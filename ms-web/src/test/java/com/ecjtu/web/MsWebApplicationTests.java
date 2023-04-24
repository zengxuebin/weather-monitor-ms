package com.ecjtu.web;

import com.ecjtu.web.security.SecretUtil;
import com.ecjtu.common.utils.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableConfigurationProperties
class MsWebApplicationTests {


    @Test
    public void test() {
        System.out.println(SecurityUtil.encryptPassword("123456"));
        System.out.println(SecurityUtil.matchesPassword("123456","$2a$10$Nfz1BOqt4N8/zWz.bknh/u9k5.zQjr0kcXQ8YA5z1wZzajyCV99.6"));
    }


    @Test
    public void testPwd() {
        String encrypt = SecretUtil.encrypt("123456");
        System.out.println(SecretUtil.desEncrypt(encrypt));
    }
}
