package com.litway.springcloud.alibaba.service;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.alibaba.service.impl.PaymentFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "sentinel-nacos-payment-provider", fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<?> paymentSQL(@PathVariable("id") Long id);

}
