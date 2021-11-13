package com.cnc.gateway.filter;

import com.cnc.gateway.config.DefaultRedisRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
public class GlobalRateLimitFilter implements GlobalFilter, Ordered {


    @Autowired
    DefaultRedisRateLimiter limiter;

    private static final String GATEWAY_GLOBAL_ACCESS_LIMIT = "GGAL";
    private static final String GATEWAY_DEFAULT_ROUTE = "defaultFilters";

    /**
     * 全局访问限流
     *
     * @return
     */

    private KeyResolver accessRateLimitKeyResolver() {
        return exchange -> Mono.just(GATEWAY_GLOBAL_ACCESS_LIMIT);
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return accessRateLimitKeyResolver()
                .resolve(exchange)
                .flatMap(key -> limiter.isAllowed(GATEWAY_DEFAULT_ROUTE, key)
                        .flatMap(response -> {
                            if (response.isAllowed()) {
                                return chain.filter(exchange);
                            }
                            for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                                exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                            }
                            log.error("to many request...");
                            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                            return exchange.getResponse().setComplete();
                        })
                );
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
