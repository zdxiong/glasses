package com.xp.glasses.service.impl.wechat;

import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsSpecification;
import com.xp.glasses.entity.form.CreateOrderForm;
import com.xp.glasses.entity.form.OrderGoodsParam;
import com.xp.glasses.entity.order.Order;
import com.xp.glasses.entity.order.OrderItem;
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
import java.util.stream.Collectors;

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
        List<OrderItem> orderGoodsItems = new ArrayList<>();

        OrderItem orderItem;

        StringBuffer attrBuffer;
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
            List<GoodsSpecification> spes = new ArrayList<>();
            // 获取规格
            List<String> speIds = goodsParam.getSpeIds();

            if (!CollectionUtils.isEmpty(speIds)){
                 spes = specificationService.selectBySpeIds(speIds);
            }

            // 获取该商品的所有规格
            List<GoodsSpecification> goodsSpes = specificationService.getGoodsSpes(goodsParam.getGoodsId());
            Map<String, List<GoodsSpecification>> speGroup = goodsSpes.stream().
                    collect(Collectors.groupingBy(GoodsSpecification::getName));
            boolean flag = false;
            for (String speName:speGroup.keySet()){
                List<GoodsSpecification> specifications = speGroup.get(speName);
                for (GoodsSpecification specification : specifications) {
                    if (spes.contains(specification)){
                        flag = true;
                        continue;
                    }
                }
                if (!flag){
                    return BaseResponse.build(ResponseCode.INVALID_PARAMS,"请选择商品:"+goods.getName()+"的"+speName);
                }
                flag = false;
            }

            attrBuffer = new StringBuffer();
            for (GoodsSpecification spe : spes) {
                Integer attachPrice = spe.getAttachPrice();
                if (null == attachPrice) {
                    return BaseResponse.build(ResponseCode.FAIL, "商品参数异常,请联系店主啦~");
                }
                unitPrice += attachPrice;
                attrBuffer.append(spe.getName()).append(":").append(spe.getValue()).append(";");

            }

            // 计算总价
            totalPrice += unitPrice * num;

            String goodsItemId = IdUtils.initId();
            orderItem = new OrderItem();
            orderItem.setGoodsId(goods.getId());
            orderItem.setId(goodsItemId);
            orderItem.setOrderNo(orderNo);
            orderItem.setNum(num);
            orderItem.setUnitPrice(unitPrice);
            orderItem.setAttrs(attrBuffer.toString());
            orderGoodsItems.add(orderItem);

            // 再次将单价置0,参与下一次计算
            unitPrice = 0L;


        }

        Order orderDto = initMainOrder(orderNo,orderForm.getRemarks(),
                "f4873a44506a4ba4b2b42904a741c7c4", nowTime,totalPrice,orderForm.getAddressId());

        // 保存订单主表
        saveOrderMainInfo(orderDto);

        // 保存订单商品表信息
        if (!CollectionUtils.isEmpty(orderGoodsItems)){
            saveOrderGoodsItems(orderGoodsItems);
        }

        return BaseResponse.build();
    }

    private void saveOrderGoodsItems(List<OrderItem> orderItems) {
        weChatOrderMapper.saveOrderGoodsItems(orderItems);
    }


    private void saveOrderMainInfo(Order orderDto) {
        weChatOrderMapper.saveOrderMainInfo(orderDto);
    }


    private Order initMainOrder(String orderNo, String remarks, String userId,
                                Date createTime, Long totalPrice,String addressId) {
        Order orderDto = new Order();

        orderDto.setOrderNo(orderNo);
        orderDto.setRemarks(remarks);
        // 设置订单未支付
        orderDto.setOrderStatus(Order.OrderStatus.NO_PAY);
        orderDto.setAddressId(addressId);
        // 物流
//        orderDto.setLogisticsNo(null);
        // 门店
//        orderDto.setShopId(null);
        // 用户ID
        orderDto.setUserId(userId);

//        orderDto.setWePayNo(null);
        // 记录时间
        orderDto.setCreateTime(createTime);
        orderDto.setOrderPrice(totalPrice);
        return orderDto;
    }
}
