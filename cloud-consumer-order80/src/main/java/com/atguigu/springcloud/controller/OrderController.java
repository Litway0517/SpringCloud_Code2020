package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.loadlbalance.MyRobinRule;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    // 以前是单个payment微服务, 但是更改为CLOUD-PAYMENT-SERVICE
    // private static final String PAYMENT_URL = "http://localhost:8001/payment";

    /*
        现在 支付payment微服务 内部有两台微服务运行, 向外暴露出来的微服务名称就不能是 8001 8002标志了,
        那样的话让客户端调用就会出现让客户端选择调用哪台微服务, 显然不可能.
        因此, 我们使用微服务的名称, 他们的名称是一样的, 这样能够说明. 这样 支付payment微服务集群, 对外暴露的就不再是ip:port而是服务名.
     */
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment";

    @Resource
    private MyRobinRule myRobinRule;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 支付
     *
     * @param id id
     * @return {@link CommonResult}<{@link ?}>
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult<?> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/get/" + id, CommonResult.class);
    }

    /**
     * 创建订单
     *
     * @param payment 付款
     * @return {@link CommonResult}<{@link ?}>
     */
    @GetMapping("/payment/create")
    public CommonResult<?> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommonResult.class);
    }

    /**
     * 测试轮询
     *
     * @return {@link CommonResult}<{@link String}>
     */
    @GetMapping("/payment/lb")
    public CommonResult<String> testRobinRule() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (serviceInstanceList == null || serviceInstanceList.size() == 0) {
            throw new RuntimeException("获取服务实例列表异常");
        }

        // 得到具体服务的实例
        ServiceInstance serviceInstance = myRobinRule.getServiceInstance(serviceInstanceList);
        // 根据实例得到uri
        URI uri = serviceInstance.getUri();

        // 通过uri去请求
        String result = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return new CommonResult<>(200, "请求成功", result);

    }



}
