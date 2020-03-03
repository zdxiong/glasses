package com.xp.glasses.entity.order;

import lombok.Data;

import java.util.Date;

/**
 * 订单商品
 *
 * @author Mrxiong
 */
@Data
public class OrderGoodsItemDto {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品数量
     */
    private Integer num;


    private Date createTime;

    private Date updateTime;

}
