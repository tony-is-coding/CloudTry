package com.cnc.fp.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.cnc.commons.response.APIResponse;
import com.cnc.commons.utils.AESUtil;
import com.cnc.commons.utils.MD5Util;
import com.cnc.fp.user.dao.UserInfoDOMapper;
import com.cnc.fp.user.dao.UserSecretInfoDOMapper;
import com.cnc.fp.user.dao.dataObject.UserInfoDO;
import com.cnc.fp.user.dao.dataObject.UserSecretInfoDO;
import com.cnc.fp.user.service.UserAuthService;
import com.cnc.fp.user.service.UserInfoService;
import com.cnc.fp.user.service.bo.UserInfoBO;
import com.cnc.fp.user.utils.CommonUtils;
import com.cnc.fp.user.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import java.util.UUID;


@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {


    @Autowired
    UserInfoDOMapper userInfoDOMapper;
    @Autowired
    UserSecretInfoDOMapper userSecretInfoDOMapper;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RedisUtil redisUtil;


    private UserInfoDO createUserInfoDo(UserInfoBO userInfo) {
        UserInfoDO infoDO = new UserInfoDO();
        if (userInfo == null) {
            return infoDO;
        }
        BeanUtils.copyProperties(userInfo, infoDO);
        LocalDateTime now = LocalDateTime.now();
        infoDO.setIsDeleted(Boolean.FALSE);
        infoDO.setAddDt(now);
        infoDO.setUpdateDt(now);
        return infoDO;
    }


    private UserSecretInfoDO createUserSecretInfoDo(UserInfoBO userInfo) {
        UserSecretInfoDO infoDO = new UserSecretInfoDO();
        if (userInfo == null) {
            return infoDO;
        }
        infoDO.setUserId(userInfo.getUserId());
        infoDO.setEncryptPassword(userInfo.getEncryptPassword());
        return infoDO;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void register(UserInfoBO user) {
        userInfoDOMapper.insert(createUserInfoDo(user));
        userSecretInfoDOMapper.insert(createUserSecretInfoDo(user));
    }


    @Override
    public String login(String nameOrMobile, String password) {


        String encryptedPassword = MD5Util.md5DigestAsHex(password);
        UserInfoBO userInfoBO;

        if (CommonUtils.isMobile(nameOrMobile)) {
            userInfoBO = userInfoService.searchByMobile(nameOrMobile);
        } else {
            userInfoBO = userInfoService.searchByUserName(nameOrMobile);
        }
        if (userInfoBO == null || !userInfoBO.getEncryptPassword().equals(encryptedPassword)) {
            return null;
        }
        //TODO: 已登录验证
        try {
            String token = UUID.randomUUID().toString().replace("-", "");
            redisUtil.setEx(token, JSON.toJSONString(userInfoBO), 60 * 10);
            return token;
        } catch (Exception e) {
            return null;
        }

    }
}
