package com.cnc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.cnc.gateway.service.IAuthService;
import com.cnc.provider.api.user.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    @Autowired
    IAuthService authService;


    public AuthorizationGatewayFilterFactory() {
        super(AbstractGatewayFilterFactory.NameConfig.class);
    }

    @Override
    public GatewayFilter apply(AbstractGatewayFilterFactory.NameConfig config) {
        return (exchange, chain) -> {
            log.info(config.getName() + " executed....");
            ServerHttpRequest request = exchange.getRequest();
            List<String> varList = request.getHeaders().get("accesstoken");
            UserInfoDTO userInfo = null;
            if (varList != null && varList.size() != 0) {
                String accessToken = varList.get(0);
                userInfo = authService.authorization(accessToken);
            }
            if (userInfo != null) {
                String userInfoPayload = Base64.getEncoder().encodeToString(JSON.toJSONString(userInfo).getBytes());
                ServerHttpRequest nextRequest = exchange.getRequest().mutate().header("user-info", userInfoPayload).build();
                chain.filter(exchange.mutate().request(nextRequest).build());
            }
            return chain.filter(exchange);
        };
    }

}
