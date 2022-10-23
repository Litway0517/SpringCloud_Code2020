package com.litway.springcloud.service.impl;


import com.litway.springcloud.mapper.AccountMapper;
import com.litway.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Resource
    private AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void deduct(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        // 模拟超时异常，全局事务回滚
        // 暂停几秒钟线程
        // try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountMapper.deduct(userId,money);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }
}
