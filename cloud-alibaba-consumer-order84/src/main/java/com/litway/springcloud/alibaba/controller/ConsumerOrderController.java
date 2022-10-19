package com.litway.springcloud.alibaba.controller;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sentinel/fallback")
public class ConsumerOrderController {

    @Value("${service-url.nacos-user-service}")
    private String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    public CommonResult<?> getPayment(@PathVariable("id") Long userId) {
        CommonResult<?> commonResult = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + userId, CommonResult.class, userId);
        return new CommonResult<>().success(commonResult);
    }

}
