package com.xp.glasses.service;

import com.xp.glasses.entity.GoodsSpecification;

import java.util.List;

/**
 * @author Mrxiong
 */
public interface SpecificationService extends BaseService {
    /**
     * 获取商品规格
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> getGoodsSpes(String goodsId);
}
