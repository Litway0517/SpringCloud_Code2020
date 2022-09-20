package com.atguigu.myrule.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 丝带规则
 *
 * @author DELL_
 * @date 2022/09/20
 */
@Configuration
public class RibbonRoundRule {

    @Bean
    public IRule myRule() {
        return new RoundRobinRule();
    }
}
