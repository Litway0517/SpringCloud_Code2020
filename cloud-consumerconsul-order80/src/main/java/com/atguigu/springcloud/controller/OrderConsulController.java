package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 订单层
 *
 * @author DELL_
 * @date 2022/10/12
 */
@RestController
public class OrderConsulController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://CONSUL-PROVIDER-PAYMENT/payment";


    /**
     * 消费者
     *
     * @return {@link String}
     */
    @GetMapping(value = "/consumer/payment/consul")
    public String consumerConsul() {
        return restTemplate.getForObject(PAYMENT_URL + "/consul", String.class);
    }

    @GetMapping(value = "/consumer/payment/getEntity/consul/{id}")
    public CommonResult<?> consumerConsulForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> response = restTemplate.getForEntity(PAYMENT_URL + "/get/" + id, CommonResult.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return new CommonResult<>(444, "操作失败~");
        }

    }

}
