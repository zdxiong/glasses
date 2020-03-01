package com.xp.glasses.entity;

import lombok.Data;


/**
 * 商品规格
 *
 * @author xiongzhendong
 * @date 2010/01/10
 */
@Data
public class GoodsSpecification<T> {

    /**
     * id
     */
    private String id;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 规格值
     */
    private T value;

    /**
     * 商品
     */
    private Goods goods;

    /**
     * 规格图片
     */
    private Image img;

    /**
     * 库存
     */
    private Integer stock;


    /**
     * 该规格附加价值
     */
    private Integer attachPrice;




    private String imageUrl;
}
