package com.cnc.commons.response;

import lombok.Data;

public class APIResponse<T> extends CommonResponse<T> {

    private String errMsg;
    private Integer statusCode;
    private T data;


    public static <T> APIResponse<T> httpOK(T data) {
        return new APIResponse<T>("", 200, data);
    }

    public static final APIResponse<String> HTTP_NOT_FOUND = new APIResponse<String>("not found !", 404, "");
    public static final APIResponse<String> HTTP_BAD_REQUEST = new APIResponse<String>("bad request !", 400, "");
    public static final APIResponse<String> HTTP_NOT_AUTHED = new APIResponse<String>("not authorization", 401, "");


    public APIResponse(String errMsg, Integer statusCode, T data) {
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
