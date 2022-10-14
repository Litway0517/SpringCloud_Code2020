package com.litway.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.PaymentFeignFallbackService;
import com.litway.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")

// 下列方法若加上HystrixCommand注解, 则表示当方法出现异常 超时等待 等情况会有兜底(没有HystrixCommand注解则没有兜底 testOKApi)
// 超时控制默认1s
// @DefaultProperties(defaultFallback = "defaultGlobalFallback")
public class OrderHystrixController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/OK/{id}")
    public CommonResult<String> testOKApi(@PathVariable("id") Integer id) {
        return paymentService.reqOK(id);
    }

    // @HystrixCommand(fallbackMethod = "testTimeoutApiHandler", commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    // })
    // @HystrixCommand
    @GetMapping("/payment/timeout/{id}")
    public CommonResult<String> testTimeoutApi(@PathVariable("id") Integer id) {
        CommonResult<String> result = paymentService.reqTimeout(id);
        return result;
    }

    public CommonResult<String> testTimeoutApiHandler(@PathVariable("id") Integer id) {
        return new CommonResult<>(200, "请求失败", "消费端80调用支付微服务8001失败，系统繁忙请稍后重试");
    }

    public CommonResult<String> defaultGlobalFallback() {
        return new CommonResult<>(200, "请求失败", "系统繁忙，请稍后重试~");
    }



}
