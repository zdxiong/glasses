package com.xp.glasses.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null){
            return false;
        }
        GoodsSpecification goodsSpecification = (GoodsSpecification) o;
        if (goodsSpecification.getId().equals(this.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, goods, img, stock, attachPrice, imageUrl);
    }
}
