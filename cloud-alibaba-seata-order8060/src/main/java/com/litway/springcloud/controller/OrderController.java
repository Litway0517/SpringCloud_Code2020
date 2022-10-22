package com.litway.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.domain.Order;
import com.litway.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/test-api")
    public CommonResult<?> testApi() {
        return new CommonResult<>().success();
    }

    @GetMapping("/create")
    public CommonResult<?> create(Order order) {
        orderService.create(order);
        return new CommonResult<>().success("创建成功");
    }
}
