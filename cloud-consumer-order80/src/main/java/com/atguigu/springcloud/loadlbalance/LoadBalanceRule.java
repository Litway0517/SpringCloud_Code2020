package com.atguigu.springcloud.loadlbalance;


import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载平衡规则
 *
 * @author DELL_
 * @date 2022/10/13
 */
public interface LoadBalanceRule {

    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList);

}
