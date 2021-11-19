package com.cnc.fp.user.dao;

import com.cnc.fp.user.dao.dataObject.UserSecretInfoDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecretInfoDOMapper {


    int insert(UserSecretInfoDO record);

    UserSecretInfoDO queryByUserId(Integer userId);

}