package com.litway.springcloud.service.impl;

import com.litway.springcloud.service.PaymentService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FactoryPaymentServiceImpl implements FallbackFactory<PaymentService> {


    @Override
    public PaymentService create(Throwable throwable) {
        log.info(throwable.getMessage());
        return new PaymentServiceImpl();
    }
}
