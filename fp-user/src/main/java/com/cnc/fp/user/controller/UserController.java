package com.cnc.fp.user.controller;


import com.cnc.commons.response.APIResponse;
import com.cnc.fp.user.controller.vo.UserBaseInfoResponseVO;
import com.cnc.fp.user.controller.vo.UserRegisterRequestVO;
import com.cnc.fp.user.service.UserInfoService;
import com.cnc.fp.user.service.bo.UserInfoBO;
import com.cnc.provider.api.user.enums.RegisterMode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping(path = "/register")
    public APIResponse<?> register(@RequestBody UserRegisterRequestVO userRegisterVo) {
        UserInfoBO user = new UserInfoBO();
        BeanUtils.copyProperties(userRegisterVo, user);
        user.setIsDeleted(false);
        user.setRegMode(RegisterMode.MOBILE.getCode().byteValue());
        user.setThirdPartyUserId("");
        user.setUserId(1);
        userInfoService.register(user);
        return APIResponse.httpOK("register succeed!!!");
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public APIResponse<?> info() {
        Integer userId = 1;
        UserInfoBO infoBO = userInfoService.searchById(userId);
        if (infoBO == null) {
            return APIResponse.httpOK(new UserBaseInfoResponseVO());
        }
        UserBaseInfoResponseVO vo = new UserBaseInfoResponseVO();
        vo.setAge(infoBO.getAge());
        vo.setGender(infoBO.getGender());
        vo.setMobile(infoBO.getMobile());
        vo.setName(infoBO.getName());
        return APIResponse.httpOK(vo);
    }

}
