package com.litway.springcloud.service;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignFallbackService implements PaymentService {


    @Override
    public CommonResult<String> reqOK(Integer id) {
        return new CommonResult<>(200, "请求失败", "reqOK系统繁忙，请稍后重试~");
    }

    @Override
    public CommonResult<String> reqTimeout(Integer id) {
        return new CommonResult<>(200, "请求失败", "reqTimeout系统繁忙，请稍后重试~");
    }
}
