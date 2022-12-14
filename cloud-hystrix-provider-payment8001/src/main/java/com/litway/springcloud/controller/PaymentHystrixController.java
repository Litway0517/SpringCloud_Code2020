package com.litway.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * hystrix中间件
 *
 * @author DELL_
 * @date 2022/12/20
 */
@RestController
@RequestMapping("/consumer/hystrix")
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/payment/OK/{id}")
    public CommonResult<String> reqOK(@PathVariable("id") Integer id) {
        // int i = 10 / 0;
        String result = paymentHystrixService.reqOK(id);
        log.info(result);
        return new CommonResult<>(200, "请求成功", result);
    }

    @GetMapping("/payment/timeout/{id}")
    public CommonResult<String> reqTimeout(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.reqTimeout(id);
        log.info(result);
        return new CommonResult<>(200, "请求成功", result);
    }


    // 服务熔断
    @GetMapping("/circuit/break/{id}")
    public CommonResult<String> circuitBreak(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        return new CommonResult<>(200, "请求成功", result);
    }


}
