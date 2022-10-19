package com.litway.springcloud.alibaba.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.alibaba.myfallback.MyFallback;
import com.litway.springcloud.alibaba.myhandler.MyHandler;
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

    /*
        SentinelResource注解的几个参数解释
     */
    @GetMapping("/consumer/{id}")
    // 没有配置 -> 会导致接口的错误直接返回给前端界面
    // @SentinelResource(value = "fallback")

    // 有fallback配置 -> 出现错误时会执行服务降级逻辑. fallback -> Throwable
    // @SentinelResource(value = "fallback", fallbackClass = {MyFallback.class}, fallback = "fallbackHandler")

    // 有blockHandler配置 -> 违反sentinel控制台规则时触blockHandler逻辑
    // @SentinelResource(value = "blockHandler", blockHandlerClass = {MyHandler.class}, blockHandler = "myHandler")

    // fallback和blockHandler均有, 当请求发生错误时, 且违背限流规则时, 优先返回blockHandler方法, 其实blockHandler是一个特殊的500
    // exceptionsToIgnore参数配置忽略的异常类型(不走兜底类), 如当接口执行过程中遇到IllegalArgumentException类型的错误时不走兜底类中的方法
    @SentinelResource(value = "fallbackAndBlockHandler",
            fallbackClass = {MyFallback.class}, fallback = "fallbackHandler",
            blockHandlerClass = {MyHandler.class}, blockHandler = "myHandler",
            exceptionsToIgnore = {IllegalArgumentException.class}
    )
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
