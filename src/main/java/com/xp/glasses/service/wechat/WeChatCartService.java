package com.xp.glasses.service.wechat;

import com.xp.glasses.entity.car.JoinBuyCarForm;
import com.xp.glasses.entity.car.ModifyCarParam;
import com.xp.glasses.utils.BaseResponse;

/**
 * @author Mrxiong
 */
public interface WeChatCartService {
    /**
     * 加入购物车
     * @param joinBuyCarForm
     * @return
     */
    BaseResponse joinCart(JoinBuyCarForm joinBuyCarForm);

    /**
     * 获取用户购物车商品列表
     * @param userId
     * @return
     */
    BaseResponse cartGoods(String userId);

    /**
     * 修改购物车信息
     * @param carParam
     * @return
     */
    BaseResponse modifyBuycar(ModifyCarParam carParam);

    BaseResponse deleteBuycar(String id);
}
