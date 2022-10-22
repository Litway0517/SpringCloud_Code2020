package com.litway.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/test-api")
    public CommonResult<?> testApi() {
        return new CommonResult<>().success();
    }


}
