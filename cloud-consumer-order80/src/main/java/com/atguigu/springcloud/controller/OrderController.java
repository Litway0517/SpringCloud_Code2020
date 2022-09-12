package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    // 以前是单个payment微服务, 但是更改为CLOUD-PAYMENT-SERVICE
    // private static final String PAYMENT_URL = "http://localhost:8001/payment";

    /*
        现在 支付payment微服务 内部有两台微服务运行, 向外暴露出来的微服务名称就不能是 8001 8002标志了,
        那样的话让客户端调用就会出现让客户端选择调用哪台微服务, 显然不可能.
        因此, 我们使用微服务的名称, 他们的名称是一样的, 这样能够说明. 这样 支付payment微服务集群, 对外暴露的就不再是ip:port而是服务名.
     */
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment";


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommonResult.class);
    }


}
