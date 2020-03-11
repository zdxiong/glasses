package com.xp.glasses.web.wechat;

import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.form.CreateOrderForm;
import com.xp.glasses.service.wechat.WeChatOrderService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/order")
public class WeChatOrderController {

    @Autowired
    WeChatOrderService weChatOrderService;

    @PostMapping("create")
    public BaseResponse createOrder(@RequestBody CreateOrderForm orderForm){

        if (Objects.isNull(orderForm)){
            return BaseResponse.build(ResponseCode.INVALID_PARAMS,"参数错误.");
        }

        if (StringUtils.isEmpty(orderForm.getUserId())){
            return BaseResponse.build(ResponseCode.INVALID_PARAMS,"请登录.");
        }


        if (CollectionUtils.isEmpty(orderForm.getGoods())){
            return BaseResponse.build(ResponseCode.INVALID_PARAMS,"请选择商品.");
        }


        if (orderForm.getAddressId() == null){
            return BaseResponse.build(ResponseCode.INVALID_PARAMS,"请选择收货地址.");
        }

        return weChatOrderService.createOrder(orderForm);
    }


    /**
     * 根据用户购物车中商品获取预付订单信息
     * @param userId
     * @return
     */
    @RequestMapping("preOrderByCart")
    public BaseResponse preOrderInfoByCart(String userId){

        if (StringUtils.isEmpty(userId)){
            return BaseResponse.build(ResponseCode.INVALID_PARAMS,"请登录.");
        }
        return weChatOrderService.preOrderInfoByCart(userId);

    }


    @RequestMapping("allOrders")
    public BaseResponse getAllOrders(String userId){

        if (StringUtils.isEmpty(userId)){
            return BaseResponse.build("请登录");
        }
        return weChatOrderService.getAllOrders(userId);

    }


    @RequestMapping("deleteOrder")
    public BaseResponse deleteOrder(String orderNo){
        if (StringUtils.isEmpty(orderNo)){
            return BaseResponse.build("请选择您要删除的订单");
        }

        return weChatOrderService.deleteOrder(orderNo);
    }
}
