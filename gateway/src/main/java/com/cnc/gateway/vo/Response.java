package com.cnc.gateway.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class Response implements Serializable {

    private int code;
    private String message;

    public static final Response GLOBAL_TIMEOUT_ERROR = new Response(10000, "严重错误发生!!!");


    public static final Response ECHO_TIMEOUT_ERROR = new Response(20000, "ECHO 服务现在不可用!!!");
}
