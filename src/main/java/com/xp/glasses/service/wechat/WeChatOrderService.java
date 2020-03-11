package com.xp.glasses.service.wechat;

import com.xp.glasses.entity.form.CreateOrderForm;
import com.xp.glasses.utils.BaseResponse;

/**
 * @author Mrxiong
 */
public interface WeChatOrderService {
    /**
     * 创建订单
     * @param orderForm
     * @return
     */
    BaseResponse createOrder(CreateOrderForm orderForm);

    /**
     * 根据购物车获取预付订单信息
     * @param userId
     * @return
     */
    BaseResponse preOrderInfoByCart(String userId);

    /**
     * 获取所有订单
     * @param userId
     * @return
     */
    BaseResponse getAllOrders(String userId);

    /**
     * 删除订单
     * @param orderNo
     * @return
     */
    BaseResponse deleteOrder(String orderNo);
}
