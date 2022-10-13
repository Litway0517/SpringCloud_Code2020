package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 付款控制器
 *
 * @author DELL_
 * @date 2022/09/11
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /*
        @DiscoveryService服务发现 的注解. 能够得到Eureka Server中注册的一些信息.
        例如, 有哪些微服务, 具体对应的微服务下面又有多少实例, 具体实例中的URI, port等信息.
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功, server-port = " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    @PostMapping(value = "/payment/create")
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功, server-port = " + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }


    /*
        测试DiscoveryClient这个类中有什么信息
     */
    @GetMapping(name = "test", value = "/payment/discoveryClient")
    public CommonResult<DiscoveryClient> discoveryClient() {
        List<String> services = discoveryClient.getServices();
        services.forEach(s -> {
            log.info("Eureka Server中的微服务名称：" + s);
        });

        // 直接获得cloud-payment-service对应的实例有多少
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(s -> {
            log.info("ServiceId: " + s.getServiceId());
            log.info("Host: " + s.getHost());
            log.info("Port: " + s.getPort());
            log.info("URL: " + s.getUri());
            log.info("Scheme: " + s.getScheme());
            log.info("\n");
        });
        return new CommonResult<DiscoveryClient>(200, "DiscoveryClient相关信息", discoveryClient);
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }


}


