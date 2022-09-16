package com.atguigu.springcloud;

import com.atguigu.myrule.config.RibbonRandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient

/*
    在启动该微服务的时候就能去加载我们的自定义Ribbon配置类, 从而使配置生效.

    官方文档明确给出了警告: 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下,
        否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享, 达不到特殊化定制的目的了.
 */
@RibbonClient(value = "CONSUL-PROVIDER-PAYMENT", configuration = {RibbonRandomRule.class})
public class OrderConsulMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class, args);
    }

}
