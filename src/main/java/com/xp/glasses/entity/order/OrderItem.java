package com.xp.glasses.entity.order;

import com.xp.glasses.entity.Goods;
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

    /**
     *  单价
     */
    private Long unitPrice;

    /**
     * 商品属性
     */
    private String attrs;


    // 具象商品
    private Goods goods;


}
