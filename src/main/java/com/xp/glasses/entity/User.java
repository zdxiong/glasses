package com.xp.glasses.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户表
 *
 * @author Mrxiong
 * @date 2020/02/10
 */
@Data
@Accessors
public class User {

    /**
     * ID
     */
    @NotNull(message = "用户Id不可为空")
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 身份证信息
     */
    private String idCard;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型
     */
    private String type;
    /**
     * 微信ID
     */
    private String weChat;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 位置(微信提供的位置信息)
     */
    private String addr;

    /**
     * 语言
     */
    private String language;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
