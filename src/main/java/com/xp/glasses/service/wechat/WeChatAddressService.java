package com.xp.glasses.service.wechat;

import com.xp.glasses.entity.ReceiveAddr;
import com.xp.glasses.utils.BaseResponse;


/**
 * @author Mrxiong
 */
public interface WeChatAddressService {

    /**
     * 添加地址
     * @param receiveAddr
     * @return
     */
    BaseResponse addReceiveAddr(ReceiveAddr receiveAddr);


    /**
     * 更新收货地址
     * @param receiveAddr
     */
    void updateReceiveAddr(ReceiveAddr receiveAddr);

    /**
     * 所有收货地址
     * @param userId
     * @return
     */
    BaseResponse addrs(String userId);

    /**
     * 默认收货地址
     * @param userId
     * @return
     */
    BaseResponse defaultAddr(String userId);

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    BaseResponse removeAddr(String id);
}
