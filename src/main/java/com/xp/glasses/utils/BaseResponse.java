package com.xp.glasses.utils;

import com.xp.glasses.constant.ResponseCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
@Getter
@Setter
@SuppressWarnings("unused")
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    private BaseResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    private BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private BaseResponse(T data, ResponseCode responseCode) {
        this.data = data;
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    private BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private BaseResponse(ResponseCode code, String msg, T data) {
        this.code = code.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseResponse build(ResponseCode responseCode, T data) {
        BaseResponse response = new BaseResponse(data, responseCode);
        return response;
    }

    public static BaseResponse build(Integer code, String msg) {
        BaseResponse response = new BaseResponse(code, msg);
        return response;
    }

    public static <T> BaseResponse build(Integer code, String msg, T data) {
        BaseResponse response = new BaseResponse(code, msg, data);
        return response;
    }

    public static <T> BaseResponse build(ResponseCode code, String msg, T data) {
        BaseResponse response = new BaseResponse(code, msg, data);
        return response;
    }

    public static BaseResponse build(ResponseCode responseCode) {
        BaseResponse response = new BaseResponse(responseCode);
        return response;
    }

    public static BaseResponse build() {
        BaseResponse response = new BaseResponse(ResponseCode.SUCCESS);
        return response;
    }
    public static <T> BaseResponse build(T data) {
        BaseResponse response = new BaseResponse(ResponseCode.SUCCESS,"success",data);
        return response;
    }
}
