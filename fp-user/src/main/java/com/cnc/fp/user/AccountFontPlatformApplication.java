package com.cnc.fp.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.cnc.fp.user.dao")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = {"com.cnc.fp.user.dubbo", "com.cnc.fp.user.service"})
public class AccountFontPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountFontPlatformApplication.class, args);
    }
}
