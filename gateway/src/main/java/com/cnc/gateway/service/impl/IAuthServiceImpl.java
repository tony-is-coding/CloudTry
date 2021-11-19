package com.cnc.gateway.service.impl;

import com.cnc.commons.response.DubboResponse;
import com.cnc.gateway.service.IAuthService;
import com.cnc.provider.api.DubboServiceVersionConfig;
import com.cnc.provider.api.user.AccountService;
import com.cnc.provider.api.user.dto.UserInfoDTO;
import io.netty.util.internal.StringUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class IAuthServiceImpl implements IAuthService {

    @DubboReference(version = DubboServiceVersionConfig.USER_SERVICE_VERSION)
    AccountService accountService;

    @Override
    public UserInfoDTO authorization(String token) {
        DubboResponse<UserInfoDTO> response = accountService.authorization(token);
        if (!StringUtil.isNullOrEmpty(response.getErrMsg())) {
            return null;
        }
        return response.getData();
    }
}
