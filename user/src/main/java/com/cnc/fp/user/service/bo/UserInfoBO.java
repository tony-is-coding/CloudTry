package com.cnc.fp.user.service.bo;

import lombok.Data;

@Data
public class UserInfoBO {

    private Integer userId;

    private String name;

    private Byte gender;

    private Short age;

    private String mobile;

    private Byte regMode;

    private String thirdPartyUserId;

    private Boolean isDeleted;

    private String encryptPassword;
}
