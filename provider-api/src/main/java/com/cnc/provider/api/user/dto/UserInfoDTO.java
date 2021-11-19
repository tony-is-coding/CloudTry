package com.cnc.provider.api.user.dto;

import com.cnc.provider.api.user.enums.RegisterMode;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 8241970228716425282L;

    private Integer userId;

    private String name;

    private Byte gender;

    private Short age;

    private String mobile;

    private RegisterMode regMode;

    private String thirdPartyUserId;

    private Boolean isDeleted;
}
