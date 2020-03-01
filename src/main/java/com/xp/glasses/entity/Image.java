package com.xp.glasses.entity;

import com.xp.glasses.constant.ImageType;
import lombok.Data;


/**
 * 图片
 *
 * @author Mrxiong
 */
@Data
public class Image {

    private String id;

    /**
     * 映射实物的ID
     */
    private String mappingId;

    /**
     * 商品访问地址
     */
    private String url;

    /**
     * 图片类型
     */
    private ImageType type;

    /**
     * 排列顺序
     */
    private byte sort;

}
