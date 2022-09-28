package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Value("config.info")
    private String configInfo;

    @GetMapping("/get/config-info")
    public CommonResult getConfigInfo() {
        return new CommonResult(200, "获取成功", configInfo);
    }

}
