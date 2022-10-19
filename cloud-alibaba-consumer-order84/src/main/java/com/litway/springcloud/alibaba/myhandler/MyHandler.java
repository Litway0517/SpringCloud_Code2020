package com.litway.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class MyHandler {

    public static CommonResult<?> myHandler(Long id, BlockException ex) {
        return new CommonResult<>().error(ex.toString());
    }

}
