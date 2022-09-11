package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 订单main80
 *
 * @author DELL_
 * @date 2022/09/10
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {

    public static void main(String[] args)
    {
        SpringApplication.run(OrderMain80.class,args);
    }
}
