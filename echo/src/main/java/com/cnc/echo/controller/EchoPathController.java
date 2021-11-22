package com.cnc.echo.controller;

import com.alibaba.fastjson.JSON;
import com.cnc.commons.response.APIResponse;
import com.cnc.provider.api.user.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class EchoPathController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/user-info")
    public APIResponse<UserInfoDTO> echoPath() {
        String userInfoPayload = request.getHeader("user-info");
        byte[] userInfoJsonBytes = Base64.getDecoder().decode(userInfoPayload);
        UserInfoDTO userInfo = JSON.parseObject(userInfoJsonBytes, UserInfoDTO.class);
        return APIResponse.httpOK(userInfo);
    }
}
