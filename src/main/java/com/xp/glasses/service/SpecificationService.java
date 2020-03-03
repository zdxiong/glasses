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

    /**
     * 根据规格ID列表 获取多个规格详情
     * @param speIds
     * @return
     */
    List<GoodsSpecification> selectBySpeIds(List<String> speIds);
}
