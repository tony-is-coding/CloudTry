package com.cnc.fp.user.controller.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBaseInfoResponseVO {
    private String name;

    private Byte gender;

    private Short age;

    private String mobile;
}
