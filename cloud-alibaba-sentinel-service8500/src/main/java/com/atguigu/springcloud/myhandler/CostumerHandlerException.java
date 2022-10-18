package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CostumerHandlerException {

    public static CommonResult<?> costumerAndParamHandler(Long id, BlockException ex) {
        return new CommonResult<>().error();
    }

    public static CommonResult<?> testCostumerNoParamHandler(BlockException ex) {
        return new CommonResult<>().error();
    }

}
