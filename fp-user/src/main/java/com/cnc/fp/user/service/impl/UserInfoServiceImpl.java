package com.cnc.fp.user.service.impl;

import com.cnc.fp.user.dao.UserInfoDOMapper;
import com.cnc.fp.user.dao.UserSecretInfoDOMapper;
import com.cnc.fp.user.dao.dataObject.UserInfoDO;
import com.cnc.fp.user.dao.dataObject.UserSecretInfoDO;
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


    private UserInfoBO createUserInfoBo(UserInfoDO userInfoDO, UserSecretInfoDO userSecretInfoDO) {
        UserInfoBO infoBO = new UserInfoBO();
        if (userInfoDO == null || userSecretInfoDO == null) {
            return infoBO;
        }
        BeanUtils.copyProperties(userSecretInfoDO, infoBO);
        BeanUtils.copyProperties(userInfoDO, infoBO);
        return infoBO;
    }

    public UserInfoBO searchById(Integer userId) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(userId);
        UserSecretInfoDO userSecretInfoDO = userSecretInfoDOMapper.queryByUserId(userId);
        return createUserInfoBo(userInfoDO, userSecretInfoDO);
    }

    @Override
    public UserInfoBO searchByUserName(String userName) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByUsername(userName);
        if (userInfoDO == null) {
            return null;
        }
        UserSecretInfoDO userSecretInfoDO = userSecretInfoDOMapper.queryByUserId(userInfoDO.getId());
        return createUserInfoBo(userInfoDO, userSecretInfoDO);
    }

    @Override
    public UserInfoBO searchByMobile(String mobile) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByMobile(mobile);
        if (userInfoDO == null) {
            return null;
        }
        UserSecretInfoDO userSecretInfoDO = userSecretInfoDOMapper.queryByUserId(userInfoDO.getId());
        return createUserInfoBo(userInfoDO, userSecretInfoDO);
    }
}
