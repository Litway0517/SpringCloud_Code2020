package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 付款
 *
 * @author DELL_
 * @date 2022/09/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {


    private Long id;
    private String serial;

}
