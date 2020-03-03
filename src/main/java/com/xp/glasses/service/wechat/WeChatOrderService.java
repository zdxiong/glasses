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
}
