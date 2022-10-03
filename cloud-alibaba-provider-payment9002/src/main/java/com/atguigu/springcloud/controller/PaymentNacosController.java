package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentNacosController {

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/payment/nacos/{id}")
    public CommonResult<String> getPayment(@PathVariable("id") Integer id) {
        String s = "nacos registry, serverPort: " + serverPort + ".\tid: " + id;
        return new CommonResult<>(200, "请求成功", s);
    }



}
