package com.litway.springcloud.mapper;


import com.litway.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    /**
     * 创建
     *
     * @param order 订单
     */
    public void create(Order order);

    /**
     * 更新
     *
     * @param userId 用户id
     * @param status 状态
     */
    public void update(@Param("userId") Long userId, @Param("status") Integer status);


}
