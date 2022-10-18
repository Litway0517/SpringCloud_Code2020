package com.atguigu.springcloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
@RequestMapping("/sentinel")
public class SentinelServiceController {

    @Resource
    private SentinelService sentinelService;

    @GetMapping(value = "/test/{name}")
    public CommonResult<?> testApiHello(@PathVariable String name) {
        String result = sentinelService.sayHello(name);
        return new CommonResult<>(200, "请求成功", result);
    }

    // *************测试 -> 流控模式之直接失败 和 关联*************
    @GetMapping(value = "/test/")
    public CommonResult<?> testApiA() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CommonResult<>(200, "请求成功", IdUtil.fastSimpleUUID());
    }


    // *************测试 -> 流控模式之关联*************
    // 订单接口
    @GetMapping("/test/order")
    public CommonResult<?> testOrder() {
        return new CommonResult<>(200, "请求成功", IdUtil.fastSimpleUUID());
    }

    // 支付接口
    @GetMapping("/test/payment")
    public CommonResult<?> testPayment() {
        return new CommonResult<>(200, "请求成功", DateUtil.date());
    }


    // *************测试 -> 流控效果之Warm up 热启动*************
    @GetMapping("/test/warmup")
    public CommonResult<?> testWarmup() {
        return new CommonResult<>().success(IdUtil.simpleUUID());
    }


    // *************测试 -> 流控效果之排队等待*************
    @GetMapping("/test/wait")
    public CommonResult<?> testWait() {
        log.info(Thread.currentThread().getName() + "\t" + "...Wait");
        return new CommonResult<>().success(IdUtil.simpleUUID());
    }


    /*
        服务降级测试
     */

    // *************测试 -> 服务降级之RT*************
    @GetMapping("/test/circuit")
    public CommonResult<?> testCircuit() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("测试RT");
        return new CommonResult<>().success("测试RT" + IdUtil.simpleUUID());
    }

    // *************测试 -> 服务降级之异常比例*************
    @GetMapping("/test/ratio")
    public CommonResult<?> testExceptionRatio() {
        log.info("测试异常比例");
        int i = 10 / 0;
        return new CommonResult<>().success("测试异常比例" + IdUtil.simpleUUID());
    }

    // *************测试 -> 服务降级之异常数*************
    @GetMapping("/test/count")
    public CommonResult<?> testExceptionCount() {
        log.info("测试异常数");
        int i = 10 / 0;
        return new CommonResult<>().success("测试异常数" + IdUtil.simpleUUID());
    }


    /*
        热点规则测试
     */
    @GetMapping("/test/testhotkey")
    @SentinelResource(value = "testhotkey", blockHandler = "testHotKeyHandler")
    public CommonResult<?> testHotKey(@RequestParam(value = "p1", required = false, defaultValue = "null") String p1,
                                       @RequestParam(value = "p2", required = false, defaultValue = "null") String p2) {
        return new CommonResult<>().success("测试正常, p1: " + p1 + ". p2: " + p2);
    }

    public CommonResult<?> testHotKeyHandler(String p1, String p2) {
        return new CommonResult<>().success("测试失败, p1: " + p1 + ". p2: " + p2);
    }



}
