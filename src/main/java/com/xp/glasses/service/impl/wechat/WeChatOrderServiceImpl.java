package com.xp.glasses.service.impl.wechat;

import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsSpecification;
import com.xp.glasses.entity.form.CreateOrderForm;
import com.xp.glasses.entity.form.OrderGoodsParam;
import com.xp.glasses.entity.order.OrderDto;
import com.xp.glasses.entity.order.OrderGoodsItemDto;
import com.xp.glasses.entity.order.OrderGoodsItemSpeDto;
import com.xp.glasses.mapper.wechat.WeChatOrderMapper;
import com.xp.glasses.service.GoodsService;
import com.xp.glasses.service.SpecificationService;
import com.xp.glasses.service.wechat.WeChatOrderService;
import com.xp.glasses.utils.BaseResponse;
import com.xp.glasses.utils.IdUtils;
import com.xp.glasses.utils.OrderCodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatOrderServiceImpl implements WeChatOrderService {

    @Autowired
    WeChatOrderMapper weChatOrderMapper;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SpecificationService specificationService;


    @Override
    public BaseResponse createOrder(CreateOrderForm orderForm) {

        Date nowTime = new Date();

        // 生成订单ID
        String orderNo = OrderCodeFactory.getOrderCode();


        // 先择的商品明细
        List<OrderGoodsParam> goodsParams = orderForm.getGoods();
        Map queryMap;
        Goods goods;
        // 总价
        Long totalPrice = 0L;
        // 单价
        Long unitPrice = 0L;

        // 订单包含的商品信息
        List<OrderGoodsItemDto> orderGoodsItems = new ArrayList<>();

        // 订单包含的商品的规格
        List<OrderGoodsItemSpeDto> orderGoodsItemSpeDtos = new ArrayList<>();

        OrderGoodsItemDto orderGoodsItemDto;

        OrderGoodsItemSpeDto orderGoodsItemSpeDto;

        for (OrderGoodsParam goodsParam : goodsParams) {
            // 数量
            Integer num = goodsParam.getNum();

            queryMap = new HashMap(2);
            queryMap.put("goodsId", goodsParam.getGoodsId());
            List<Goods> goodsList = goodsService.select(queryMap);
            if (CollectionUtils.isEmpty(goodsList) || goodsList.size() > 1) {
                return BaseResponse.build(ResponseCode.FAIL, "您的资源未找到");
            }
            // 商品
            goods = goodsList.get(0);

            String goodsItemId = IdUtils.initId();
            orderGoodsItemDto = new OrderGoodsItemDto();
            orderGoodsItemDto.setGoodsId(goods.getId());
            orderGoodsItemDto.setId(goodsItemId);
            orderGoodsItemDto.setOrderNo(orderNo);
            orderGoodsItemDto.setNum(num);
            orderGoodsItemDto.setCreateTime(nowTime);
            orderGoodsItemDto.setUpdateTime(nowTime);
            orderGoodsItems.add(orderGoodsItemDto);

            // 促销商品
            if (goods.getPromotion() == 1) {
                // 折扣价格
                Long discountsPrice = goods.getDiscountsPrice();
                if (null == discountsPrice) {
                    return BaseResponse.build(ResponseCode.FAIL, "商品参数信息异常,请联系店主啦~");
                }
                unitPrice += discountsPrice;
            }

            if (goods.getPromotion() == 0) {
                Long normalPrice = goods.getNormalPrice();
                if (null == normalPrice) {
                    return BaseResponse.build(ResponseCode.FAIL, "商品参数异常,请联系店主啦~");
                }
                unitPrice += normalPrice;
            }

            // 获取规格
            List<String> speIds = goodsParam.getSpeIds();

            List<GoodsSpecification> spes = specificationService.selectBySpeIds(speIds);

            for (GoodsSpecification spe : spes) {
                Integer attachPrice = spe.getAttachPrice();
                if (null == attachPrice) {
                    return BaseResponse.build(ResponseCode.FAIL, "商品参数异常,请联系店主啦~");
                }

                unitPrice += attachPrice;

                orderGoodsItemSpeDto = new OrderGoodsItemSpeDto();

                orderGoodsItemSpeDto.setId(IdUtils.initId());
                orderGoodsItemSpeDto.setName(spe.getName());
                orderGoodsItemSpeDto.setValue((String) spe.getValue());
                orderGoodsItemSpeDto.setOrderGoodsItemId(goodsItemId);
                orderGoodsItemSpeDto.setCreateTime(nowTime);
                orderGoodsItemSpeDto.setUpdateTime(nowTime);
                orderGoodsItemSpeDtos.add(orderGoodsItemSpeDto);
            }

            // 计算总价
            totalPrice += unitPrice * num;

            // 再次将单价置0,参与下一次计算
            unitPrice = 0L;

        }

        OrderDto orderDto = initMainOrder(orderNo,orderForm.getRemarks(),
                "f4873a44506a4ba4b2b42904a741c7c4", nowTime,totalPrice);

        // 保存订单主表
        saveOrderMainInfo(orderDto);

        // 保存订单商品表信息
        if (!CollectionUtils.isEmpty(orderGoodsItems)){
            saveOrderGoodsItems(orderGoodsItems);
        }

        // 保存规格
        if (!CollectionUtils.isEmpty(orderGoodsItemSpeDtos)){
            saveOrderGoodsSpes(orderGoodsItemSpeDtos);
        }
        return BaseResponse.build();
    }

    private void saveOrderGoodsSpes(List<OrderGoodsItemSpeDto> orderGoodsItemSpeDtos) {
        weChatOrderMapper.saveOrderGoodsSpes(orderGoodsItemSpeDtos);
    }

    private void saveOrderGoodsItems(List<OrderGoodsItemDto> orderGoodsItems) {
        weChatOrderMapper.saveOrderGoodsItems(orderGoodsItems);
    }


    private void saveOrderMainInfo(OrderDto orderDto) {
        weChatOrderMapper.saveOrderMainInfo(orderDto);
    }


    private OrderDto initMainOrder(String orderNo, String remarks, String userId, Date createTime, Long totalPrice) {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderNo(orderNo);
        orderDto.setRemarks(remarks);
        // 设置订单未支付
        orderDto.setOrderStatus(OrderDto.OrderStatus.NO_PAY);
        // 物流
//        orderDto.setLogisticsNo(null);
        // 门店
//        orderDto.setShopId(null);
        // 用户ID
        orderDto.setUserId(userId);

//        orderDto.setWePayNo(null);
        // 记录时间
        orderDto.setCreateTime(createTime);
        orderDto.setUpdateTime(createTime);
        orderDto.setOrderPrice(totalPrice);
        return orderDto;
    }
}
