package com.xp.glasses.mapper.wechat;

import com.xp.glasses.entity.order.OrderDto;
import com.xp.glasses.entity.order.OrderGoodsItemDto;
import com.xp.glasses.entity.order.OrderGoodsItemSpeDto;
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
     * 保存订单商品规格
     * @param orderGoodsItemSpeDtos
     */
    void saveOrderGoodsSpes(List<OrderGoodsItemSpeDto> orderGoodsItemSpeDtos);

    /**
     * 把偶你订单商品明细
     * @param orderGoodsItems
     */
    void saveOrderGoodsItems(List<OrderGoodsItemDto> orderGoodsItems);

    /**
     * 保存订单
     * @param orderDto
     */
    void saveOrderMainInfo(OrderDto orderDto);
}
