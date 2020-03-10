package com.xp.glasses.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品
 *
 * @author Mrxiong
 */
@Data
public class Goods {

    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String nickName;

    /**
     * 商品介绍
     */
    private String description;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品照片
     */
    private List<Image> infoImages;

    /**
     * 主页图片
     */
    private Image mainImage;

    /**
     * 商品对应的门店
     */
    private Shop shop;

    /**
     * 商品类别
     */
    private Category category;

    /**
     * 是否促销 0-否 1-是
     */
    private Integer promotion;

    /**
     * 折扣价 - 促销产品才会有折扣价
     */
    private Long discountsPrice;

    /**
     * 基础价格（正常价格）
     */
    private Long normalPrice;

    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 评价数
     */
    private Integer evaluate;

    /**
     * 是否热门 1-热门 0-非热门
     */
    private Integer hot;

    /**
     * 是否推荐 1-推荐 0-普通
     */
    private Integer recommend;

    /**
     * 是否新品 1-新品
     */
    private Integer isNew;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 所有的规格
     */
    private List<GoodsSpecification> allSpes;

/************************weChat 数据包装*******************************/
    /**
     * 规格
     */
    private Map<String,List<GoodsSpecification>> speMap;


    /**
     * 最低价格
     */
    private Double minPrice;

    /**
     * 最高价格
     */
    private Double maxPrice;
}
