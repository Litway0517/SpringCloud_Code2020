package com.atguigu.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rate")
public class RateLimitController {


    @GetMapping("/test/source")
    @SentinelResource(value = "source", blockHandler = "sourceHandler")
    public CommonResult<?> byResource() {
        return new CommonResult<>().success(new Payment(10L, "serial_test"));
    }

    public CommonResult<?> sourceHandler(BlockException ex) {
        return new CommonResult<>(400, ex.getClass().getCanonicalName() + ", 服务不可用. ");
    }

    @GetMapping("/test/url")
    @SentinelResource(value = "url")
    public CommonResult<?> byUrl() {
        return new CommonResult<>().success("操作成功");
    }


}
