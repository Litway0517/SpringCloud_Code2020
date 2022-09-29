package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public CommonResult<String> configInfo() {
        String result = "serverPort: " + serverPort + "\t\n\n configInfo: " + configInfo;
        return new CommonResult<>(200, "获取成功", result);
    }

}
