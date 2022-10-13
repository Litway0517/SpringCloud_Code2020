package com.litway.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/hystrix")
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/payment/{id}")
    public CommonResult<String> reqOK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.reqOK(id);
        log.info(result);
        return new CommonResult<>(200, "请求成功", result);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<String> reqTimeout(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.reqTimeout(id);
        log.info(result);
        return new CommonResult<>(200, "请求成功", result);
    }


}
