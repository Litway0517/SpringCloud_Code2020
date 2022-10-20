package com.litway.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamConsumerMain8801 {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain8801.class, args);
    }

}
