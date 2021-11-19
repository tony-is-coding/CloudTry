package com.cnc.fp.user.dao;

import com.cnc.fp.user.dao.dataObject.UserInfoDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDOMapper {


    int insert(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Integer id);

}