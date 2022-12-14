package com.litway.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.sleuth.instrument.web.client.feign.TraceFeignClientAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, TraceFeignClientAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class SeataStorageMain8080 {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMain8080.class, args);
    }

}
