package com.xp.glasses.service;

import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsQueryParam;
import com.xp.glasses.entity.GoodsSpecification;
import com.xp.glasses.entity.ReturnData;

import java.util.List;

/**
 * @author Mrxiong
 */
public interface GoodsService extends BaseService {
    /**
     * bootstrap-table插件 表格数据
     *
     * @param goodsQueryParam
     * @return
     */
    ReturnData bootstrapTableData(GoodsQueryParam goodsQueryParam);

    /**
     * 根据商品ID 查询规格
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> specifications(String goodsId);

    /**
     * 查询所有的商品
     * @return
     */
    List<Goods> all();
}
