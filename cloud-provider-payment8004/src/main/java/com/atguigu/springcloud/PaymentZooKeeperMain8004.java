package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 付款main8004
 *
 * @author DELL_
 * @date 2022/09/13
 */
@SpringBootApplication
// 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
@EnableDiscoveryClient
public class PaymentZooKeeperMain8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentZooKeeperMain8004.class, args);
    }

}
