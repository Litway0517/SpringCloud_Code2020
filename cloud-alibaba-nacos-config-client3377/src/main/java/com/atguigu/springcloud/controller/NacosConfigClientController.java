package com.atguigu.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 控制器中加入@RefreshScope注解, 使当前类配置支持Nacos动态刷新配置功能
@RefreshScope

@RestController
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/nacos/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
