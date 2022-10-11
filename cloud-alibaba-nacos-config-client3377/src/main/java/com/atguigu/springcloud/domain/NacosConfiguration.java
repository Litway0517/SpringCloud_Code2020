package com.atguigu.springcloud.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// @Configuration("config")
public class NacosConfiguration {

    @Value("${info}")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
