package com.xp.glasses.mapper.wechat;

import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.car.BuyCar;
import com.xp.glasses.entity.car.BuyCarItem;
import com.xp.glasses.entity.car.ModifyCarParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface WeChatCartMapper {
    /**
     * 添加购物车主表
     * @param buyCar
     */
    void joinCart(BuyCar buyCar);

    /**
     * 添加购物车商品规格
     * @param buyCarItems
     */
    void joinCartItems(List<BuyCarItem> buyCarItems);

    /**
     * 获取用户已近加入到购物车的同类商品
     * @param userId
     * @param goodsId
     * @return
     */
    List<BuyCar> getUserCartGoods(@Param("userId") String userId, @Param("goodsId") String goodsId);

    /**
     * 修改购物车
     * @param addedBuyCar
     */
    void updateBuyCart(BuyCar addedBuyCar);


    List<BuyCar> cartGoods(String userId);

    /**
     * 修改购物车信息
     * @param carParam
     */
    void modifyBuycar(ModifyCarParam carParam);


    /**
     * 删除 购物车商品
     * @param id
     */
    void deleteBuycar(String id);
}
