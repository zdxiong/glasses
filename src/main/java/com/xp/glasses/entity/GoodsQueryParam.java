package com.xp.glasses.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Mrxiong
 */
@Data
public class GoodsQueryParam {

    @NotNull(message = "确认起始页码")
    private Integer currentPage;

    @NotNull(message = "确认每页大小")
    private Integer pageSize;

    private String shopId;

    private String categoryId;

}
