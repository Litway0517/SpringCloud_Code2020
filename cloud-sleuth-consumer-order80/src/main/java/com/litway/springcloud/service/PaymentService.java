package com.litway.springcloud.service;


import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.impl.FactoryPaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-provider-payment", fallbackFactory = FactoryPaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping("/payment/nacos/{id}")
    public CommonResult<String> getPayment(@PathVariable("id") Integer id);

}
