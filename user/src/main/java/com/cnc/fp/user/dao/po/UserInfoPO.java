package com.cnc.fp.user.dao.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoPO {

    private Integer id;

    private String name;

    private Byte gender;

    private Short age;

    private String mobile;

    private Byte regMode;

    private String thirdPartyUserId;

    private Boolean isDeleted;

    private LocalDateTime addDt;

    private LocalDateTime updateDt;
}