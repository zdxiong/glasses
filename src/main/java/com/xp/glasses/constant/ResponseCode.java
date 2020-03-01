package com.xp.glasses.constant;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
@SuppressWarnings("unused")
public enum ResponseCode {

    /***
     * 成功响应
     */
    SUCCESS(0, "成功"),
    /**
     * 响应失败
     */
    FAIL(-1, "失败"),
    /**
     * 资源未找到
     */
    NOT_FOUND(10010, "不存在"),
    /**
     * 实体未找到
     */
    ENTITY_NOT_EXIST(10011, "实体信息不存在"),
    /**
     * 参数不合法
     */
    INVALID_PARAMS(10012, "请求参数不合法!");

    private Integer code;
    private String msg;


    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
