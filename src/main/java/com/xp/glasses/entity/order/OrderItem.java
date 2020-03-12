package com.xp.glasses.entity.order;

import lombok.Data;

/**
 * 订单商品
 *
 * @author Mrxiong
 */
@Data
public class OrderItem {

    /**
     * 主键ID
     */
    private String id;


    private String goodsId;

    /**
     * 订单编号
     */
    private String orderNo;


    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 单价
     */
    private Long unitPrice;

    /**
     * 商品属性
     */
    private String attrs;


    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 图片访问地址
     */
    private String url;

}
