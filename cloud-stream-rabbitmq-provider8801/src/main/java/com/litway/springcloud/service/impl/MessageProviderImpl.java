package com.litway.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.litway.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


// 不需要再写Service注解, 这里需要和Stream的binders结合
@EnableBinding
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = IdUtil.simpleUUID();
        this.output.send(MessageBuilder.withPayload(serial).build());
        log.info("输出消息: {}", serial);
        return null;
    }
}
