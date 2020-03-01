package com.xp.glasses.entity;

import lombok.Data;

/**
 * 商品类别
 * @author Mrxiong
 */
@Data
public class Category {
    private String id;
    private String parentId;
    private String name;
}
