package com.xp.glasses.entity.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Mrxiong
 */
@Data
public class GoodsForm {

    /**
     * 商品ID
     */
    private String id;

    /**
     * 商品名称
     */
    @NotBlank(message = "请输入商品标题")
    @NotNull(message = "请输入商品标题")
    private String name;

    /**
     * 商品昵称
     */
    @NotBlank(message = "请输入商品附标题")
    @NotNull(message = "请输入商品附标题")
    private String nickName;

    /**
     * 商品描述
     */
    @NotBlank(message = "请输入商品描述")
    @NotNull(message = "请输入商品描述")
    private String description;

    /**
     * 销量
     */
    private Integer sales;


    /**
     * 商品对应的门店
     */
    @NotBlank(message = "请选择门店")
    @NotNull(message = "请选择门店")
    private String shopId;

    /**
     * 商品类别
     */
    @NotBlank(message = "请选择商品类别")
    @NotNull(message = "请选择商品类别")
    private String categoryId;

    /**
     * 品牌
     */
    private String brand;


    /**
     * 库存
     */
    @NotNull(message = "请输入库存")
    private Integer stock;






    /**
     * 是否热门 1-热门 0-非热门
     */
    @NotNull(message = "请选择是否热门")
    private Integer hot;

    /**
     * 是否推荐 1-推荐 0-普通
     */
    @NotNull(message = "请选择是否推荐")
    private Integer recommend;

    /**
     * 是否新品 1-新品
     */
    @NotNull(message = "请选择是否新品")
    private Integer isNew;

    /**
     * 是否促销
     */
    @NotNull(message = "请选择是否促销")
    private Integer promotion;


    /**
     * 折扣价 元
     */
    private Double discountsPrice;


    /**
     * 基础价格（正常价格）
     */
    @NotNull(message = "请输入正常价格")
    private Integer normalPrice;


    /**
     * 市场价
     */
    @NotNull(message = "请输入市场价格")
    private Double marketPrice;


    @NotNull(message = "请选择一张图片")
    private MultipartFile image;

    /**
     * 创建时间
     */
    private Date createTime;

}
