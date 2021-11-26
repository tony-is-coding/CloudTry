package com.cnc.fp.user.dao;

import com.cnc.fp.user.dao.po.UserSecretInfoPO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecretInfoDOMapper {


    int insert(UserSecretInfoPO record);

    UserSecretInfoPO queryByUserId(Integer userId);

}