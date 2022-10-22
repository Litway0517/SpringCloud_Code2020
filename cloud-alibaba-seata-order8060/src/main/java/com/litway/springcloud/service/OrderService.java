package com.litway.springcloud.service;


import com.litway.springcloud.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);


}
