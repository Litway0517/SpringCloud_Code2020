package com.atguigu.springcloud.controller;


import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myhandler.CostumerHandlerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 速率限制控制器
 *
 * @author DELL_
 * @date 2022/11/19
 */
@RestController
@RequestMapping("/rate")
public class RateLimitController {

    /**
     * 通过资源设定限流规则
     *
     * @return {@link CommonResult}<{@link ?}>
     */
    @GetMapping("/test/source")
    @SentinelResource(value = "source", blockHandler = "sourceHandler")
    public CommonResult<?> byResource() {
        return new CommonResult<>().success(new Payment(10L, "serial_test"));
    }

    /**
     * 降级方法
     * @param ex 异常
     * @return {@link CommonResult}<{@link ?}>
     */
    public CommonResult<?> sourceHandler(BlockException ex) {
        return new CommonResult<>(400, ex.getClass().getCanonicalName() + ", 服务不可用. ");
    }

    /**
     * 通过url设定先流规则
     *
     * @return {@link CommonResult}<{@link ?}>
     */
    @GetMapping("/test/url")
    @SentinelResource(value = "url")
    public CommonResult<?> byUrl() {
        return new CommonResult<>().success("操作成功");
    }


    /**
     * blockHandlerClass: 降级类
     * blockHandler: 降级方法
     *
     * @param userId 用户id
     * @return {@link CommonResult}<{@link ?}>
     */
    // 测试自定义的处理方法
    @GetMapping("/test/costumerAndParam/{id}")
    @SentinelResource(value = "costumerAndParam",
            // 被流控时执行兜底的类
            blockHandlerClass = CostumerHandlerException.class,
            // 指定类中的兜底方法
            blockHandler = "costumerAndParamHandler"
    )
    public CommonResult<?> costumerAndParam(@PathVariable("id") Long userId) {
        return new CommonResult<>().success(new Payment(20L, "有参数: " + IdUtil.fastSimpleUUID() + userId.toString()));
    }

    /**
     * 无参数降级方法
     *
     * @return {@link CommonResult}<{@link ?}>
     */
    @GetMapping("/test/costumerNoParam")
    @SentinelResource(value = "costumerNoParam",
            blockHandlerClass = CostumerHandlerException.class,
            blockHandler = "testCostumerNoParamHandler"
    )
    public CommonResult<?> testCostumerNoParam() {
        return new CommonResult<>().success(new Payment(20L, "无参数: " + IdUtil.fastSimpleUUID()));
    }




}
