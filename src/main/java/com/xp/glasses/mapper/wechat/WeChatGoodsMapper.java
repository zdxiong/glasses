package com.xp.glasses.mapper.wechat;

import com.xp.glasses.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface WeChatGoodsMapper {

    /**
     * 获取推荐产品
     * @return
     */
    List<Goods> recommendGoods();

    /**
     * 获取所有的商品列表
     * @param offset
     * @param pageSize
     * @return
     */
    List<Goods> allGoodsList();


    /**
     *
     */
    Integer goodsCount();

    /**
     * 获取商品详情信息
     * @param goodsId
     * @return
     */
    Goods goodsDetail(String goodsId);
}
