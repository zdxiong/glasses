package com.xp.glasses.entity.order;

import com.xp.glasses.entity.ReceiveAddr;
import com.xp.glasses.entity.Shop;
import com.xp.glasses.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单DTO
 * @author Mrxiong
 */
@Data
public class Order {

    /**************************************表字段*********************************************/
    /**
     * 订单编号,主键ID
     */
    private String orderNo;

    /**
     * 微信支付编号
     */
    private String wePayNo;

    /**
     * 物流编号
     */
    private String logisticsNo;

    /**
     *  用户Id
     */
    private String  userId;


    /**
     * 收货地址ID
     */
    private String addressId;


    /**
     * 门店ID
     */
    private String shopId;

    /**
     * 订单金额
     */
    private Long orderPrice;

    /**
     * 订单备注
     */
    private String remarks;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 成交时间
     */
    private Date completeTime;

    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 收货人电话
     */
    private String receivePhone;
    /**
     * 收货人地址
     */
    private String addrs;

    /**************************************表字段 end*********************************************/


    private User user;

    private ReceiveAddr addr;

    private Shop shop;

    private List<OrderItem> orderItems;

    private Integer num;

    public enum OrderStatus{
        /**
         * 未支付
         */
        NO_PAY,
        /**
         * 已支付
         */
        PAY,
        /**
         * 订单取消
         */
        CANCEL,
        /**
         * 运输中
         */
        TRANSPORT,
        /**
         * 已完成
         */
        COMPLETE,
        /**
         * 退款中
         */
        REFUNDING,
        /**
         *已退款
         */
        REFUNDED
    }
}
