package com.cnc.gateway;


import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix // 允许 hystrix 作为断路器生效
@SpringBootApplication
@EnableCircuitBreaker  // 允许熔断开启
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "com.cnc.gateway")
@EnableCreateCacheAnnotation
@EnableDubbo(scanBasePackages = "com.cnc.gateway.service")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
