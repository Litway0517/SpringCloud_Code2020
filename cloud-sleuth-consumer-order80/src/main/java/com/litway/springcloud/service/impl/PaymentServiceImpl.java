package com.litway.springcloud.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult<String> getPayment(Integer id) {
        return new CommonResult<String>().success("系统繁忙");
    }
}
