package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
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

    @GetMapping("/payment/timeout/{id}")
    public CommonResult<String> testTimeoutApi(@PathVariable("id") Integer id) {
        return paymentService.reqTimeout(id);
    }

}
