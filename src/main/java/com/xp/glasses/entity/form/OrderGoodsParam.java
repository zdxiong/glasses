package com.xp.glasses.entity.form;

import lombok.Data;

import java.util.List;

/**
 * 选择的商品项目
 * @author Mrxiong
 */
@Data
public class OrderGoodsParam {

    /**
     * 商品ID
     */
    String goodsId;

    /**
     * 商品数量
     */
    Integer num;

    /**
     * 被选中的规格ID
     */
    List<String> speIds;

}
