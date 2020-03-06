package com.xp.glasses.entity.car;

import lombok.Data;

/**
 * 购物车修改
 * @author Mrxiong
 */

@Data
public class ModifyCarParam {



    private String id;

    // 数量
    private Integer num;

    // 是否选择
    private Integer  choose;


}
