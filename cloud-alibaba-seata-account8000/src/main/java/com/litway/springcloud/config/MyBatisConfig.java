package com.litway.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.litway.springcloud.mapper"})
public class MybatisConfig {


}
