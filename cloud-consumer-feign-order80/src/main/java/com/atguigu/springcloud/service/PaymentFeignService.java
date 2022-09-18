package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "CONSUL-PROVIDER-PAYMENT")
public interface PaymentFeignService {

    // 返回的结果要和微服务中rest接口的返回值一致
    @RequestMapping(value = "/payment/consul")
    String paymentConsul();

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/openfeign/timeout")
    CommonResult<Payment> paymentFeignTimeout();
}
