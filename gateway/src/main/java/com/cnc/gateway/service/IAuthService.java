package com.cnc.gateway.service;


import com.cnc.provider.api.user.dto.UserInfoDTO;

/**
 * 认证服务
 */
public interface IAuthService {
    UserInfoDTO authorization(String token);
}
