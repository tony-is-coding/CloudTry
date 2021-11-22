package com.cnc.commons.response;

public class APIResponse<T> extends CommonResponse<T> {

    private String errMsg;
    private Integer statusCode;
    private T data;


    public static <T> APIResponse<T> httpOK(T data) {
        return new APIResponse<T>("", 200, data);
    }

    public static final APIResponse<String> HTTP_NOT_FOUND = new APIResponse<String>("not found !", 404, "");

    public APIResponse(String errMsg, Integer statusCode, T data) {
        this.errMsg = errMsg;
        this.statusCode = statusCode;
        this.data = data;
    }

    public static <T> APIResponse<T> unAuthorization(String errMsg) {
        return new APIResponse<T>(errMsg, 401, null);
    }

    public static <T> APIResponse<T> badRequest(String errMsg) {
        return new APIResponse<T>(errMsg, 400, null);
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
