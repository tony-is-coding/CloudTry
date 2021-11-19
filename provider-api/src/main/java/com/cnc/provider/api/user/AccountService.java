package com.cnc.provider.api.user;

import com.cnc.commons.response.DubboResponse;
import com.cnc.provider.api.user.dto.UserInfoDTO;

public interface AccountService {
    DubboResponse<Object> createUser(UserInfoDTO userInfoDTO);

    DubboResponse<UserInfoDTO> getUserInfo(Integer userId);

    DubboResponse<UserInfoDTO> authorization(String accessToken);
}
