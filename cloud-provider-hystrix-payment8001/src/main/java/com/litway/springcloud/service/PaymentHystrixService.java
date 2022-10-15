package com.litway.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {

    /**
     * 正常访问
     */
    // 该方法如果异常会返回错误信息 不会指定默认的降级方法
    public String reqOK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     */
    /*
        fallbackMethod参数 -> 当出现异常情况时, 如调用超时|发生异常, 指定兜底的方法
        commandProperties参数 -> 设置方法正常运行的峰值参数为3s, 超过3s则执行fallbackMethod指定的兜底方法
     */
    @HystrixCommand(fallbackMethod = "reqTimeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String reqTimeout(Integer id) {

        // int i = 10 / 0;

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "  paymentInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O";
    }

    public String reqTimeoutHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "系统繁忙，请稍后重试。id：" + id;
    }


    // demo: 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 开启 服务熔断
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 设置 请求次数阈值
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 设置 窗口时间期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 设置 失败比率
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
            // 在窗口时间期之内, 请求次数达到了10次, 且失败6次, 那么断路器开启, 服务熔断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}

