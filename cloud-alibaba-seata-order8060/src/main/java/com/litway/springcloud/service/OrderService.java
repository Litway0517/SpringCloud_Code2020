package com.litway.springcloud.service;


import com.litway.springcloud.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    public void create(Order order);


}
