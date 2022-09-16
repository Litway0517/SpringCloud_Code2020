package com.atguigu.myrule.config;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置类注入
@Configuration
public class RibbonConfig {

    // 该配置类
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }

}
