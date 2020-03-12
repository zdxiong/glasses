package com.xp.glasses.mapper.wechat;

import com.xp.glasses.entity.order.Order;
import com.xp.glasses.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface WeChatOrderMapper {


    /**
     * 把偶你订单商品明细
     * @param orderGoodsItems
     */
    void saveOrderGoodsItems(List<OrderItem> orderGoodsItems);

    /**
     * 保存订单
     * @param orderDto
     */
    void saveOrderMainInfo(Order orderDto);

    /**
     * 获取用户的所有订单
     * @return
     */
    List<Order> getAllOrders(String userId);

    /**
     * 删除订单
     * @param orderNo
     */
    void deleteOrder(String orderNo);

    /**
     * 获取订单详情
     * @param orderNo
     * @return
     */
    Order orderDetail(String orderNo);
}
