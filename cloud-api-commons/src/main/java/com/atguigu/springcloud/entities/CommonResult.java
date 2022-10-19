package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    public CommonResult<T> success(T data) {
        return new CommonResult<>(200, "请求成功", data);
    }

    public CommonResult<T> error() {
        return new CommonResult<>(400, "操作失败");
    }

    public CommonResult<T> error(T data) {
        return new CommonResult<>(400, "操作失败", data);
    }
}
