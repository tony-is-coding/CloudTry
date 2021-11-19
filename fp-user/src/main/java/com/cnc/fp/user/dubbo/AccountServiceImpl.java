package com.cnc.fp.user.dubbo;


import com.cnc.commons.response.DubboResponse;
import com.cnc.fp.user.service.UserInfoService;
import com.cnc.fp.user.service.bo.UserInfoBO;
import com.cnc.provider.api.DubboServiceVersionConfig;
import com.cnc.provider.api.user.AccountService;
import com.cnc.provider.api.user.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(version = DubboServiceVersionConfig.USER_SERVICE_VERSION)
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public DubboResponse<Object> createUser(UserInfoDTO userInfoDTO) {
        return null;
    }

    @Override
    public DubboResponse<UserInfoDTO> getUserInfo(Integer userId) {
        return null;
    }

    @Override
    public DubboResponse<UserInfoDTO> authorization(String accessToken) {
        log.info("token is:" + accessToken);
        UserInfoDTO userInfo = new UserInfoDTO();
        Integer userId = 1;
        UserInfoBO infoBO = userInfoService.searchById(userId);
        BeanUtils.copyProperties(infoBO, userInfo);
        return DubboResponse.ok(userInfo);
    }
}
