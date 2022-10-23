package com.litway.springcloud.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {

    public void deduct(@Param("productId") Long productId, @Param("count") Integer count);

}
