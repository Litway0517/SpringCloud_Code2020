package com.litway.springcloud.service;

import com.litway.springcloud.domain.Order;
import com.litway.springcloud.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 创建订单
     *
     * @param order 订单
     */
    @Override
    public void create(Order order) {
        orderMapper.create(order);
    }
}
