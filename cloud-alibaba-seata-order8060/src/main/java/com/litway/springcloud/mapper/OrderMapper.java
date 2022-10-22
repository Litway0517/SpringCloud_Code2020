package com.litway.springcloud.mapper;


import com.litway.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    public void create(Order order);


}
