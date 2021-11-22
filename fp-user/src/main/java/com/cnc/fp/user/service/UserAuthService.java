package com.cnc.fp.user.service;

import com.cnc.fp.user.service.bo.UserInfoBO;

public interface UserAuthService {
    void register(UserInfoBO user);

    String login(String nameOrMobile, String password);
}
