package com.cnc.fp.account.service.impl;

import com.cnc.fp.account.dao.UserInfoDOMapper;
import com.cnc.fp.account.dao.UserSecretInfoDOMapper;
import com.cnc.fp.account.dao.dataObject.UserInfoDO;
import com.cnc.fp.account.dao.dataObject.UserSecretInfoDO;
import com.cnc.fp.account.service.UserInfoService;
import com.cnc.fp.account.service.bo.UserInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        infoDO.setAge(userInfo.getAge());
        infoDO.setGender(userInfo.getGender());
        infoDO.setMobile(userInfo.getMobile());
        infoDO.setName(userInfo.getName());
        infoDO.setIsDeleted(Boolean.FALSE);
        infoDO.setThirdPartyUserId(userInfo.getThirdPartyUserId());
        infoDO.setRegMode(userInfo.getRegMode());
        return infoDO;
    }

    private UserSecretInfoDO createUserSecretInfoDo(UserInfoBO userInfo) {
        UserSecretInfoDO infoDO = new UserSecretInfoDO();
        infoDO.setEncryptPassword(userInfo.getEncryptPassword());
        return infoDO;

    }

    private UserInfoBO createUserInfoBo(UserInfoDO userInfoDO, UserSecretInfoDO userSecretInfoDO) {
        UserInfoBO infoBO = new UserInfoBO();
        infoBO.setAge(userInfoDO.getAge());
        infoBO.setGender(userInfoDO.getGender());
        infoBO.setMobile(userInfoDO.getMobile());
        infoBO.setName(userInfoDO.getName());
        infoBO.setIsDeleted(Boolean.FALSE);
        infoBO.setThirdPartyUserId(userInfoDO.getThirdPartyUserId());
        infoBO.setRegMode(userInfoDO.getRegMode());
        infoBO.setEncryptPassword(userSecretInfoDO.getEncryptPassword());
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
