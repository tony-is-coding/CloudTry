package com.cnc.fp.account.service;

import com.cnc.fp.account.service.bo.UserInfoBO;

public interface UserInfoService {

    UserInfoBO searchById(Integer userId);

    Boolean checkIfExist(Integer userId);

    void register(UserInfoBO user);
}
