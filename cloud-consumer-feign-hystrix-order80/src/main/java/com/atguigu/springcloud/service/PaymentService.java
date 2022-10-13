package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-provider-hystrix-payment")
public interface PaymentService {

    @GetMapping("/payment/OK/{id}")
    public CommonResult<String> reqOK(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    public CommonResult<String> reqTimeout(@PathVariable("id") Integer id);

}
