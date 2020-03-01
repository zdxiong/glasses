package com.xp.glasses.mapper;

import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface GoodsMapper extends BaseMapper {
    /**
     * 查询所有的商品总数
     * @param map
     * @return
     */
    Integer count(Map map);

    /**
     * 据据商品ID查询规格
     *
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> specifications(String goodsId);

    /**
     * 所有商品
     * @return
     */
    List<Goods> all();
}
