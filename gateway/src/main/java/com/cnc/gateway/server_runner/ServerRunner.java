package com.cnc.gateway.server_runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements ApplicationRunner {

    @Value("${spring.rabbitmq.host:localhost}")
    private String rabbitmqHost;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[self]: server had initial succeed...");
        System.out.println(rabbitmqHost);
    }
}
