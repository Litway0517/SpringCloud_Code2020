package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class OrderHystrixController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/OK/{id}")
    public CommonResult<String> testOKApi(@PathVariable("id") Integer id) {
        return paymentService.reqOK(id);
    }

    @HystrixCommand(fallbackMethod = "testTimeoutApiHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/payment/timeout/{id}")
    public CommonResult<String> testTimeoutApi(@PathVariable("id") Integer id) {
        CommonResult<String> result = paymentService.reqTimeout(id);
        return result;
    }

    public CommonResult<String> testTimeoutApiHandler(@PathVariable("id") Integer id) {
        return new CommonResult<>(200, "请求失败", "消费端80调用支付微服务8001失败，系统繁忙请稍后重试");
    }



}
