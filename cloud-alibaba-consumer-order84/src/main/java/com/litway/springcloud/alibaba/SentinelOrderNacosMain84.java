package com.litway.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Sentinel
 *
 * @author DELL_
 * @date 2022/12/09
 */
@EnableDiscoveryClient
@SpringBootApplication

// 开启OpenFeign功能
@EnableFeignClients
public class SentinelOrderNacosMain84
{
    public static void main(String[] args) {
        SpringApplication.run(SentinelOrderNacosMain84.class, args);
    }
}
