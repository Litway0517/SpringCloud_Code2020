package com.litway.springcloud.service;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// @RequestMapping("/consumer/hystrix")
@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFeignFallbackService.class)

public interface PaymentService {

    @GetMapping("/consumer/hystrix/payment/OK/{id}")
    public CommonResult<String> reqOK(@PathVariable("id") Integer id);

    @GetMapping("/consumer/hystrix/payment/timeout/{id}")
    public CommonResult<String> reqTimeout(@PathVariable("id") Integer id);

}
