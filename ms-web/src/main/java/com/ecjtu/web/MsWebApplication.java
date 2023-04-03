package com.ecjtu.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ecjtu")
public class MsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsWebApplication.class, args);
    }

}
