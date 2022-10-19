package com.litway.springcloud.alibaba.myfallback;

import com.atguigu.springcloud.entities.CommonResult;

public class MyFallback {

    public static CommonResult<?> fallbackHandler(Long id, Throwable e) {
        return new CommonResult<>().error(e.getMessage());
    }


}
