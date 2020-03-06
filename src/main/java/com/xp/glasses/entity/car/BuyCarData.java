package com.xp.glasses.entity.car;

import lombok.Data;

import java.util.List;

/**
 * @author Mrxiong
 */
@Data
public class BuyCarData {

    /**
     * 商品列表
     */
    private List<BuyCar> carGoodsList;

    /**
     * 购物车总价
     */
    private Long totalPrice;

}
