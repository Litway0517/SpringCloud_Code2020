package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 纳科配置客户端控制器
 *
 * @author DELL_
 * @date 2022/10/06
 */

// 控制器中加入@RefreshScope注解, 使当前类配置支持Nacos动态刷新配置功能
@RefreshScope

@RestController
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    /**
     * 获取配置信息
     *
     * @return {@link CommonResult}<{@link String}>
     */
    @GetMapping("/nacos/config/info")
    public CommonResult<String> getConfigInfo() {
        return new CommonResult<>(200, "请求成功", configInfo);
    }
}
