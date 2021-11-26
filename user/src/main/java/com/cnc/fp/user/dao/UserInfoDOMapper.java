package com.cnc.fp.user.dao;

import com.cnc.fp.user.dao.po.UserInfoPO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDOMapper {

    int insert(UserInfoPO record);

    UserInfoPO selectByPrimaryKey(Integer id);

    UserInfoPO selectByUsername(String userName);

    UserInfoPO selectByMobile(String mobile);

}