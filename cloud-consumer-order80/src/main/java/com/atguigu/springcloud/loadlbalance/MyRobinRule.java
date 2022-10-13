package com.atguigu.springcloud.loadlbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询算法
 *
 * @author DELL_
 * @date 2022/10/13
 */
@Component
public class MyRobinRule implements LoadBalanceRule {

    // 用来技术当前请求为第几次
    private AtomicInteger currentReqCount = new AtomicInteger(0);

    // 使用AtomicInteger和自旋锁控制 访问顺序 的原子性
    public final int getAndIncrement() {
        // 注意这些变量要定义在外面, 不然访问不到
        int current;
        int next;

        do {
            // 当前值
            current = this.currentReqCount.get();
            next = current == Integer.MAX_VALUE ? 0 : current + 1;
            // while判断 -> 如果期望值和原来值相等(期望值就是原来的值), 那么就将next赋值给原来的值, 并返回true
        } while (!this.currentReqCount.compareAndSet(current, next));
        System.out.println("**********第几次访问，次数next：" + next);
        return next;
    }

    // 根据请求的顺序确定本次请求获取得到的具体服务实例
    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
