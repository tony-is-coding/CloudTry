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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    private UserInfoBO createUserInfoBo(UserInfoDO userInfoDO, UserSecretInfoDO userSecretInfoDO) {
        UserInfoBO infoBO = new UserInfoBO();
        if (userInfoDO == null || userSecretInfoDO == null) {
            return infoBO;
        }
        BeanUtils.copyProperties(userSecretInfoDO, infoBO);
        BeanUtils.copyProperties(userInfoDO,infoBO);
        return infoBO;
    }

    public UserInfoBO searchById(Integer userId) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(userId);
        UserSecretInfoDO userSecretInfoDO = userSecretInfoDOMapper.queryByUserId(userId);
        return createUserInfoBo(userInfoDO, userSecretInfoDO);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void register(UserInfoBO user) {
        userInfoDOMapper.insert(createUserInfoDo(user));
        userSecretInfoDOMapper.insert(createUserSecretInfoDo(user));
    }
}
