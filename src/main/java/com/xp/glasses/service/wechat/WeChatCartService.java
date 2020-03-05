package com.xp.glasses.service.wechat;

import com.xp.glasses.entity.car.JoinBuyCarForm;
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
}
