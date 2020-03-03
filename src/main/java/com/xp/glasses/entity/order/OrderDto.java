package com.xp.glasses.entity.order;

import lombok.Data;

import java.util.Date;

/**
 * 订单DTO
 * @author Mrxiong
 */
@Data
public class OrderDto {

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
     * 修改时间
     */
    private Date updateTime;



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
