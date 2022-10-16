package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.SentinelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/sentinel")
public class SentinelServiceController {

    @Resource
    private SentinelService sentinelService;

    @GetMapping(value = "/test/{name}")
    public CommonResult<?> testApiHello(@PathVariable String name) {
        String result = sentinelService.sayHello(name);
        return new CommonResult<>(200, "请求成功", result);
    }

    @GetMapping(value = "/test/")
    public CommonResult<?> testApiA() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CommonResult<>(200, "请求成功", IdUtil.fastSimpleUUID());
    }

}
