package com.litway.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient

// 开启OpenFeign
@EnableFeignClients
public class SleuthConsumerMain80 {

    public static void main(String[] args) {
        SpringApplication.run(SleuthConsumerMain80.class, args);
    }

}
