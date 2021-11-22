package com.cnc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnc.commons.response.APIResponse;
import com.cnc.gateway.service.IAuthService;
import com.cnc.provider.api.user.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.util.Make;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    @Autowired
    IAuthService authService;

    private static final ArrayList<String> WHITE_URL_PATH = new ArrayList<>(Arrays.asList("/api/login", "api/user/login"));

    public AuthorizationGatewayFilterFactory() {
        super(AbstractGatewayFilterFactory.NameConfig.class);
    }

    @Override
    public GatewayFilter apply(AbstractGatewayFilterFactory.NameConfig config) {
        return (exchange, chain) -> {
            log.info(config.getName() + " executed....");
            ServerHttpRequest request = exchange.getRequest();
            if (CollectionUtils.contains(WHITE_URL_PATH.iterator(), request.getPath().toString())) {
                // 白名单直接过
                return chain.filter(exchange);
            }
            List<String> varList = request.getHeaders().get("accesstoken");
            UserInfoDTO userInfo = null;
            if (varList != null && varList.size() != 0) {
                String accessToken = varList.get(0);
                userInfo = authService.authorization(accessToken);
            }
            if (userInfo != null) {
                String userInfoPayload = Base64.getEncoder().encodeToString(JSON.toJSONString(userInfo).getBytes());
                ServerHttpRequest nextRequest = exchange.getRequest().mutate().header("user-info", userInfoPayload).build();
                return chain.filter(exchange.mutate().request(nextRequest).build());
            } else {
                return authFail(exchange);
            }
        };
    }


    private Mono<Void> authFail(ServerWebExchange exchange) {
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse()
                .writeWith(Flux
                        .just(exchange
                                .getResponse()
                                .bufferFactory()
                                .wrap(JSONObject.toJSONBytes(APIResponse.unAuthorization("用户未登录!!!")))));
    }


}
