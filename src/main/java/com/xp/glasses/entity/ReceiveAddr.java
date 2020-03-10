package com.xp.glasses.entity;

import lombok.Data;

/**
 * 收货地址
 *
 * @author Mrxiong
 */
@Data
public class ReceiveAddr {

    /*************************表字段*************************/
    private String id;

    /**
     * 收货人
     */
    private String receiveName;

    /**
     * 收货人电话
     */
    private String receivePhone;


    /**
     * 收货地址明细
     */
    private String addr;

    /**
     * 当前收货地址 0-否 1-是
     */
    private Integer current;

    /**
     * 用户ID
     */
    private String userId;


    /*************************表字段 end*************************/


    private User user;

}
