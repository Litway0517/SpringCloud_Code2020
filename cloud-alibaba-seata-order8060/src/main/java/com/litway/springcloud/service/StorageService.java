package com.litway.springcloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-seata-storage")
public interface StorageService {

    @GetMapping("/storage/decrease")
    public void decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
