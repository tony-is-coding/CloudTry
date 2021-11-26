package com.cnc.fp.user.service.impl;

import com.cnc.fp.user.dao.UserInfoDOMapper;
import com.cnc.fp.user.dao.UserSecretInfoDOMapper;
import com.cnc.fp.user.dao.po.UserInfoPO;
import com.cnc.fp.user.dao.po.UserSecretInfoPO;
import com.cnc.fp.user.service.UserInfoService;
import com.cnc.fp.user.service.bo.UserInfoBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDOMapper userInfoDOMapper;
    @Autowired
    UserSecretInfoDOMapper userSecretInfoDOMapper;


    public Boolean checkIfExist(Integer userId) {
        UserInfoBO userInfoBO = searchById(userId);
        return userInfoBO == null;
    }


    private UserInfoBO createUserInfoBo(UserInfoPO userInfoPO, UserSecretInfoPO userSecretInfoPO) {
        UserInfoBO infoBO = new UserInfoBO();
        if (userInfoPO == null || userSecretInfoPO == null) {
            return infoBO;
        }
        BeanUtils.copyProperties(userSecretInfoPO, infoBO);
        BeanUtils.copyProperties(userInfoPO, infoBO);
        return infoBO;
    }

    public UserInfoBO searchById(Integer userId) {
        UserInfoPO userInfoPO = userInfoDOMapper.selectByPrimaryKey(userId);
        UserSecretInfoPO userSecretInfoPO = userSecretInfoDOMapper.queryByUserId(userId);
        return createUserInfoBo(userInfoPO, userSecretInfoPO);
    }

    @Override
    public UserInfoBO searchByUserName(String userName) {
        UserInfoPO userInfoPO = userInfoDOMapper.selectByUsername(userName);
        if (userInfoPO == null) {
            return null;
        }
        UserSecretInfoPO userSecretInfoPO = userSecretInfoDOMapper.queryByUserId(userInfoPO.getId());
        return createUserInfoBo(userInfoPO, userSecretInfoPO);
    }

    @Override
    public UserInfoBO searchByMobile(String mobile) {
        UserInfoPO userInfoPO = userInfoDOMapper.selectByMobile(mobile);
        if (userInfoPO == null) {
            return null;
        }
        UserSecretInfoPO userSecretInfoPO = userSecretInfoDOMapper.queryByUserId(userInfoPO.getId());
        return createUserInfoBo(userInfoPO, userSecretInfoPO);
    }
}
