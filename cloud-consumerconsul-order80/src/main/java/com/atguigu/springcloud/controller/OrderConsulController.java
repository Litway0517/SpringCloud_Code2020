package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://CONSUL-PROVIDER-PAYMENT/payment";

    @GetMapping(value = "/consumer/payment/consul")
    public String consumerConsul() {
        return restTemplate.getForObject(PAYMENT_URL + "/consul", String.class);
    }

}
