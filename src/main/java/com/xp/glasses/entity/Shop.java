package com.xp.glasses.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 门店
 *
 * @author Mrxiong
 */
@Data
public class Shop  extends Page{

    /**
     * 门店ID
     */
    @NotNull
    @NotBlank
    private String id;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 店长
     */
    private String shopkeeper;
    /**
     * 固定电话
     */
    private String landLine;

    /**
     * 移动电话
     */
    private String cellphone;

    /**
     * 上班时间
     */
    private String startTime;

    /**
     * 下班时间
     */
    private String quittingTime;

    /**
     * 工作日
     */
    private String workDay;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店照片
     */
    private List<Image> images;

    /**
     * 铺面介绍
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}
