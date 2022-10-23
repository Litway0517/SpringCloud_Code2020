package com.litway.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/decrease")
    public CommonResult<?> deduct(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.deduct(userId,money);
        return new CommonResult<>().success("扣减账户余额成功");
    }
}
