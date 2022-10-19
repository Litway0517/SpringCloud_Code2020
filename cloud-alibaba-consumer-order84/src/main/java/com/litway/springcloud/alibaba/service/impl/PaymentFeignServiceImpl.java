package com.litway.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.litway.springcloud.alibaba.service.PaymentFeignService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {

    @Override
    public CommonResult<?> paymentSQL(Long id) {
        return new CommonResult<>().error(new Payment(id, "errorSerial"));
    }
}
