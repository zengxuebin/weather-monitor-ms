package com.ecjtu.web;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableConfigurationProperties
class MsWebApplicationTests {


    @Test
    public void test() {
        System.out.println(StringUtils.isNotEmpty(""));
        System.out.println(StringUtils.isNotBlank(""));
        System.out.println(StringUtils.isNotBlank(null));
        System.out.println(StringUtils.isNotEmpty(null));
    }


}
