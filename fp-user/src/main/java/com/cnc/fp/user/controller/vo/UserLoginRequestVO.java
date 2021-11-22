package com.cnc.fp.user.controller.vo;


import lombok.Data;

@Data
public class UserLoginRequestVO {
    String nameOrMobile;
    String password;
}
