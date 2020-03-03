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
}
