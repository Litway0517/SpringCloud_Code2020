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

    /**
     * restTemplates调用getForEntity得到的是一个更具体的返回对象, 而getForObject是一个简化对象
     *
     * @param id id
     * @return {@link CommonResult}<{@link ?}>
     */
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
