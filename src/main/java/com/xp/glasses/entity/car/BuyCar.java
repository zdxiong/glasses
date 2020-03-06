package com.xp.glasses.entity.car;

import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsSpecification;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 购物车
 * @author Mrxiong
 */

@Data
public class BuyCar {


    /****************************表字段******************************/
    /**
     * ID 主键
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 是否选中
     */
    private Integer choose;

    /**
     * 0-失效 1-启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /****************************表字段end******************************/



    private List<BuyCarItem> buyCarItems;

    /**
     * 商品
     */
    private Goods goods;

    /**
     * 规格
     */
    private List<GoodsSpecification> spes;

    /**
     * 规格属性
     */
    private String speAttr;

    /**
     * 单价
     */
    private Long unitPrice;


}
