package com.litway.springcloud.service.impl;

import com.litway.springcloud.domain.Order;
import com.litway.springcloud.mapper.OrderMapper;
import com.litway.springcloud.service.AccountService;
import com.litway.springcloud.service.OrderService;
import com.litway.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单服务impl
 *
 * @author DELL_
 * @date 2022/11/03
 */
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
     * 创建
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 下订单->减库存->减余额->改状态
     *
     * @param order 订单
     */
    @Override
    // @GlobalTransactional(name = "test_litway_tx_group", rollbackForClassName = "Exception")
    @GlobalTransactional(name = "test_litway_tx_group", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("-----> 开始创建订单");
        orderMapper.create(order);

        log.info("-----> 订单微服务调用库存, 扣减库存Count");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("-----> 订单微服务调用库存, 扣减库存Money");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("-----> 修改订单状态");
        orderMapper.update(order.getUserId(), 0);
    }
}
