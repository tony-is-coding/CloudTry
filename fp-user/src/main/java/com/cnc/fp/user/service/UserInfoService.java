package com.cnc.fp.user.service;

import com.cnc.fp.user.service.bo.UserInfoBO;

public interface UserInfoService {

    UserInfoBO searchById(Integer userId);

    Boolean checkIfExist(Integer userId);

    void register(UserInfoBO user);
}
