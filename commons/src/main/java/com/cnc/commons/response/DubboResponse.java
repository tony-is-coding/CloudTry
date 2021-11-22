package com.cnc.commons.response;

import lombok.Data;


public class DubboResponse<T> extends CommonResponse<T> {
    private String errMsg;
    private Integer statusCode;
    private T data;

    public static <T> DubboResponse<T> ok(T data) {
        return new DubboResponse<T>(null, 200, data);
    }

    public static <T> DubboResponse<T> fail(Integer statusCode, String errMsg, T data) {
        return new <T>DubboResponse<T>(errMsg, statusCode, data);
    }


    public static final DubboResponse<?> BAD_REQUEST = new DubboResponse<Object>("bad request", 10400, null);
    public static final DubboResponse<?> OP_NOT_ALLOWED = new DubboResponse<Object>("operate not allowed", 10403, null);
    public static final DubboResponse<?> UN_AUTHORIZATION = new DubboResponse<Object>("un authorization error", 10401, null);

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
