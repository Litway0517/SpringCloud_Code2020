package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 假装顺序控制器
 *
 * @author DELL_
 * @date 2022/09/17
 */
@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/consul/test")
    public CommonResult<String> consul()
    {
        String s = paymentFeignService.paymentConsul();
        return new CommonResult<>(200, "OpenFeign请求成功", s);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        CommonResult<Payment> paymentById = paymentFeignService.getPaymentById(id);
        return paymentById;
    }

    /**
     * 超时测试
     *
     * @return {@link CommonResult}<{@link Payment}>
     */
    @GetMapping("/consumer/payment/openfeign/timeout")
    public CommonResult<Payment> feignTimeout() {
        CommonResult<Payment> result = paymentFeignService.paymentFeignTimeout();
        return result;
    }
}
