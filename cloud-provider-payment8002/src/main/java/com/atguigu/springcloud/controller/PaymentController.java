package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 付款控制器
 *
 * @author DELL_
 * @date 2022/09/11
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPost;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功, server-port = " + serverPost, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功, server-port = " + serverPost, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

}


