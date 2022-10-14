package com.litway.springcloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {

    /**
     * 正常访问
     */
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
}

