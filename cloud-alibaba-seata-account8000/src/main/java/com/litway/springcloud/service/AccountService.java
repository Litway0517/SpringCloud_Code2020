package com.litway.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


/**
 * @author DELL_
 * @date 2022/10/27
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    public void deduct(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
