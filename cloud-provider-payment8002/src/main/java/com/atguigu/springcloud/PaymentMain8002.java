package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 付款main8001
 *
 * @author DELL_
 * @date 2022/09/09
 */
@SpringBootApplication
// 开启Eureka Client客户端, 使微服务进入到Eureka Server服务器
@EnableEurekaClient
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
