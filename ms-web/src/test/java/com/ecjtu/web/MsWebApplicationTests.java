package com.ecjtu.web;

import org.junit.jupiter.api.Test;
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


    @Test
    public void testPwd() {
        String original = "高温预警-黄色";
        String[] parts = original.split("-");
        String modified = parts[1] + parts[0];
        System.out.println(modified);
    }
}
