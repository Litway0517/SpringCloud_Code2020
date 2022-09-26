package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


// 别忘了加上
@Component
@Slf4j
public class MyGatewayFilter implements GlobalFilter, Ordered {

    /*
        Mono<Void> Mono这个对象就像ModelAndView, 只不过进行了封装
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        String username = params.getFirst("username");
        log.info("请求的用户名：{}", username);

        assert username != null;
        if (!username.equals("litway")) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            exchange.getResponse().setComplete();
            return null;
        } else {
            return chain.filter(exchange);
        }
    }

    /*
        这个方法是ordered接口的方法, 用来表明过滤器执行的顺序
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
