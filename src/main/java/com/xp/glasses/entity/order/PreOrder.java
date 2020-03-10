package com.xp.glasses.entity.order;

import lombok.Data;

import java.util.List;

/**
 * 预付单信息
 * @author Mrxiong
 */
@Data
public class PreOrder {

    /**
     * 总计数量
     */
    private Integer num;

    /**
     * 总计价格
     */
    private Integer totalPrice;

    /**
     * 商品
     */
    private List<PreGoods> orderGoods;


    /**
     * 被选中结算的购物车ID
     */
    private List<String> cartIds;
}
