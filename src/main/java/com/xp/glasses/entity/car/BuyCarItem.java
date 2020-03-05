package com.xp.glasses.entity.car;

import com.xp.glasses.entity.GoodsSpecification;
import lombok.Data;

/**
 * 购物车商品的属性
 *
 * @author Mrxiong
 */
@Data
public class BuyCarItem {

    /**********************表开始**********************/
    /**
     * 购物车ID
     */
    private String carId;

    /**
     * 规格id
     */
    private String speId;

    /**********************表结束**********************/


    /**
     * 规格详情
     */
    private GoodsSpecification specification;


}
