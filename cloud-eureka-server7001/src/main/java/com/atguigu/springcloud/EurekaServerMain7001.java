package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 尤里卡服务器main7001
 *
 * @author DELL_
 * @date 2022/09/11
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7001.class, args);
    }

}
