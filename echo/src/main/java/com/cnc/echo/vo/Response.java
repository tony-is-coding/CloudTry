package com.cnc.echo.vo;

public class Response {

    private int code;
    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
