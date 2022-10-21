package com.litway.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sleuth")
public class SleuthConsumerController {

    @Resource
    private PaymentService paymentService;


    @GetMapping("/consumer/payment/{id}")
    public CommonResult<?> getPayment(@PathVariable("id") Integer userId) {
        return paymentService.getPayment(userId);
    }


}
