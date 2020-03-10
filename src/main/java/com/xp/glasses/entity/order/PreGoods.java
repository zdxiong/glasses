package com.xp.glasses.entity.order;

import com.xp.glasses.entity.GoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * 预付订单中商品信息
 * @author Mrxiong
 */
@Data
public class PreGoods {

    private String cartId;


    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 商品名
     */
    private String name;
    /**
     * 主图
     */
    private String imageUrl;
    /**
     * 选择的数量
     */
    private Integer num;
    /**
     * 是否市折扣商品
     */
    private Integer promotion;


    /**
     * 单价
     */
    private Integer unitPrice;

    /**
     * 属性
     */
    private List<String> attrs;

    /**
     * 选择的属性ID
     */
    private List<String> chooseSpesIds;


    /****基础数据用于计算 单价，属性，被选择的规格ID****/
    private List<GoodsSpecification> goodsSpecifications;

    private Integer discountsPrice; // 折扣价格

    private Integer normalPrice; // 正常价格

}
