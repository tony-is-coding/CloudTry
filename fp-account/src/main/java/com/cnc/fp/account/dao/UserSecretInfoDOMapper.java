package com.cnc.fp.account.dao;

import com.cnc.fp.account.dao.dataObject.UserSecretInfoDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecretInfoDOMapper {


    int insert(UserSecretInfoDO record);

    UserSecretInfoDO queryByUserId(Integer userId);

}