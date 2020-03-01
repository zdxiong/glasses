package com.xp.glasses.entity;

import com.xp.glasses.constant.BannerType;
import com.xp.glasses.constant.ImageType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Banner
 *
 * @author Mrxiong
 * @Date 2010/01/10
 */
@Data
public class Banner {

    /**
     * bannerId
     */
    private String id;

    /**
     * banner name
     */
    private String name;

    /**
     *
     */
    private String mappingId;

    /**
     * 描述
     */
    private String description;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 排列序号
     */
    private byte sort;

    /**
     * 创建时间
     */
    private Date createTime;

    private BannerType bannerType;
}
