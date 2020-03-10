package com.xp.glasses.service.impl;

import com.xp.glasses.entity.ReceiveAddr;
import com.xp.glasses.mapper.wechat.WeChatAddressMapper;
import com.xp.glasses.service.wechat.WeChatAddressService;
import com.xp.glasses.utils.BaseResponse;
import com.xp.glasses.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatAddressServiceImpl implements WeChatAddressService {

    @Autowired

    WeChatAddressMapper weChatAddressMapper;

    @Override
    public BaseResponse addReceiveAddr(ReceiveAddr receiveAddr) {

        receiveAddr.setId(IdUtils.initId());

        if (receiveAddr.getCurrent() ==1){
            weChatAddressMapper.resetCurrentAddr(receiveAddr.getUserId());
        }
        weChatAddressMapper.addReceiveAddr(receiveAddr);
        return BaseResponse.build();

    }

    @Override
    public BaseResponse addrs(String userId) {
        return BaseResponse.build(weChatAddressMapper.addrs(userId));
    }

    @Override
    public void updateReceiveAddr(ReceiveAddr receiveAddr) {

        Integer current = receiveAddr.getCurrent();

        // 表示设置该地址为默认地址
        if (current == 1) {
            weChatAddressMapper.resetCurrentAddr(receiveAddr.getUserId());
        }

        weChatAddressMapper.updateReceiveAddr(receiveAddr);

    }


    @Override
    public BaseResponse defaultAddr(String userId) {

        List<ReceiveAddr> addrs = weChatAddressMapper.addrs(userId);
        for (ReceiveAddr receiveAddr : addrs) {
            if (receiveAddr.getCurrent() == 1){
                return BaseResponse.build(receiveAddr);
            }
        }
        return BaseResponse.build();
    }

    @Override
    public BaseResponse removeAddr(String id) {
        weChatAddressMapper.removeAddr(id);
        return BaseResponse.build();
    }
}
