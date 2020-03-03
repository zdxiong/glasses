package com.xp.glasses.entity.order;

import lombok.Data;

import java.util.Date;

/**
 * 订单下商品规格表
 *
 * @author Mrxiong
 */
@Data
public class OrderGoodsItemSpeDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 订单商品项id
     */
    private String orderGoodsItemId;

    /**
     * 规格名
     */
    private String name;

    /**
     * 规格值
     */
    private String value;

    private Date createTime;

    private Date updateTime;

}

