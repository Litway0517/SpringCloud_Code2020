package com.litway.springcloud.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * 订单
 *
 * @author DELL_
 * @date 2022/10/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**
     * 订单状态: 0: 创建中; 1: 已完成
     */
    private Integer status;

}
