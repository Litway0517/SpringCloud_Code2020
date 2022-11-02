package com.litway.springcloud.service;


import com.litway.springcloud.domain.Order;

/**
 * 订单服务
 *
 * @author DELL_
 * @date 2022/11/02
 */
public interface OrderService {

    /**
     * 创建订单
     */
    public void create(Order order);


}
