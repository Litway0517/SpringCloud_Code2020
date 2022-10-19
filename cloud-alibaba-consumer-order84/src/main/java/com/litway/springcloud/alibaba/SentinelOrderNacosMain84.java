package com.litway.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelOrderNacosMain84
{
    public static void main(String[] args) {
        SpringApplication.run(SentinelOrderNacosMain84.class, args);
    }
}
