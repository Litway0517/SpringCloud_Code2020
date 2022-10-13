package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 应用程序上下文配置
 *
 * @author DELL_
 * @date 2022/09/10
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    // 使RestTemplate具有负载均衡能力

    // 关闭ribbon负载均衡, 使用controller层编写的规则
    // @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
