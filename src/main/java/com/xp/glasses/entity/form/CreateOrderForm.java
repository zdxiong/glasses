package com.xp.glasses.entity.form;

import lombok.Data;

import java.util.List;

/**
 * 创建订单表单
 * @author Mrxiong
 */
@Data
public class CreateOrderForm {

    private String userId;
    /**
     * 商品
     */
    private List<OrderGoodsParam> goods;

    /**
     * 收货地址ID
     */

    private String addressId;

    /**
     * 备注
     */
    private String remarks;



    private List<String> cartIds;
}
