package com.litway.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.litway.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 存储控制器
 *
 * @author DELL_
 * @date 2022/11/04
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @GetMapping("/decrease")
    public CommonResult<?> deductStorage(Long productId, Integer count) {
        storageService.deduct(productId, count);
        return new CommonResult<>().success("库存扣减成功");
    }

}
