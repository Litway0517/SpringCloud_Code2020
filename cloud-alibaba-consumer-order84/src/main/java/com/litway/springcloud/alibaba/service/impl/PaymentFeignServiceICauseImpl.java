package com.litway.springcloud.alibaba.service.impl;

import com.litway.springcloud.alibaba.service.PaymentFeignService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PaymentFeignServiceICauseImpl implements FallbackFactory<PaymentFeignService> {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentFeignServiceICauseImpl.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";

    @Override
    public PaymentFeignService create(Throwable throwable) {
        String msg = throwable == null ? "" : throwable.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new PaymentFeignServiceImpl();
    }
}
