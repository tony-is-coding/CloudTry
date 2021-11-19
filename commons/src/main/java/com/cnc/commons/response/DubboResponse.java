package com.cnc.commons.response;

import lombok.Data;


public class DubboResponse<T> extends CommonResponse<T> {
    private String errMsg;
    private Integer statusCode;
    private T data;

    public static <T> DubboResponse<T> ok(T data) {
        return new DubboResponse<T>(null, 200, data);
    }

    public static final DubboResponse<String> BAD_REQUEST = new DubboResponse<String>("bad request", 10400, "");
    public static final DubboResponse<String> OP_NOT_ALLOWED = new DubboResponse<String>("operate not allowed", 10401, "");

    public DubboResponse(String errMsg, Integer statusCode, T data) {
        this.errMsg = errMsg;
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
