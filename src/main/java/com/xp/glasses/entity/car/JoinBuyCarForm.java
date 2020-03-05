package com.xp.glasses.entity.car;

import lombok.Data;

import java.util.List;

/**
 * 加入购物车 表单数据类
 * @author Mrxiong
 */
@Data
public class JoinBuyCarForm {

    String userId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 数量选择
     */
    private Integer num;

    /**
     * 规格选择
     */
    private List<String> spes;
}
