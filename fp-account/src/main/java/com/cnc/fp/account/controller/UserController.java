package com.cnc.fp.account.controller;


import com.cnc.fp.account.controller.vo.UserRegisterRequestVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/user")
public class UserController {

    @PostMapping(path = "/register")
    public void register(@RequestParam UserRegisterRequestVO vo) {
        
    }
}
