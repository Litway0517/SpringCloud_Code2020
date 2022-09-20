package com.atguigu.myrule.config;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 带随机规则
 *
 * @author DELL_
 * @date 2022/09/20
 */

// 配置类注入
@Configuration
public class RibbonRandomRule {

    // 该配置类
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }

}
