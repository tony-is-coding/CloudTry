package com.cnc.fp.user.controller.vo;


import lombok.Data;

@Data
public class UserRegisterRequestVO {
    private String name;

    private Byte gender;

    private Short age;

    private String mobile;

    private String encryptPassword; // aslkdjalkjsdoiquwoieuqlnlkda
}
