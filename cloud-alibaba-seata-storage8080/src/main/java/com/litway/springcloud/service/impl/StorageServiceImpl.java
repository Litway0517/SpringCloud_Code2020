package com.litway.springcloud.service.impl;

import com.litway.springcloud.mapper.StorageMapper;
import com.litway.springcloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void deduct(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageMapper.deduct(productId, count);
        LOGGER.info("------->storage-service中扣减库存结束");
    }
}
