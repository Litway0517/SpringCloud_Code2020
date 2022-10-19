package com.litway.springcloud.alibaba.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sentinel/fallback")
public class ConsumerOrderController {

    @Value("${service-url.nacos-user-service}")
    private String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    @SentinelResource(value = "fallback")
    public CommonResult<?> getPayment(@PathVariable("id") Long userId) {
        CommonResult<?> commonResult = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + userId, CommonResult.class, userId);

        if (userId == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常....");
        } else {
            assert commonResult != null;
            if (commonResult.getData() == null) {
                throw new NullPointerException("NullPointerException, 该ID没有对应记录,空指针异常");
            }
        }

        return new CommonResult<>().success(commonResult);
    }

}
