package com.litway.springcloud.service.impl;

import com.litway.springcloud.domain.Order;
import com.litway.springcloud.mapper.OrderMapper;
import com.litway.springcloud.service.AccountService;
import com.litway.springcloud.service.OrderService;
import com.litway.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * 创建订单
     *
     * @param order 订单
     */
    @Override
    public void create(Order order) {
        log.info("-----> 开始创建订单");
        orderMapper.create(order);

        log.info("-----> 订单微服务调用库存, 扣减库存");
        storageService.decrease(order.getProductId(), order.getCount());
    }
}
