package com.xp.glasses.web.wechat;

import com.xp.glasses.entity.ReceiveAddr;
import com.xp.glasses.service.wechat.WeChatAddressService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/addr")
public class WeChatAddressController {


    @Autowired
    WeChatAddressService weChatAddressService;

    @PostMapping
    public BaseResponse addReceiveAddr(@RequestBody ReceiveAddr receiveAddr){

        if (null == receiveAddr){
            return BaseResponse.build("请输入收货地址相关信息.");
        }

        if (StringUtils.isEmpty(receiveAddr.getUserId())){
            return BaseResponse.build("请登录.");
        }

        if (StringUtils.isEmpty(receiveAddr.getReceiveName())){
            return BaseResponse.build("请输入收货人姓名.");
        }

        if (StringUtils.isEmpty(receiveAddr.getReceivePhone())){
            return BaseResponse.build("请输入收货人电话.");
        }

        if (StringUtils.isEmpty(receiveAddr.getAddr())){
            return BaseResponse.build("请输入收货人地址.");
        }
        return weChatAddressService.addReceiveAddr(receiveAddr);
    }


    @GetMapping
    public BaseResponse  addrs(String userId){
        if (StringUtils.isEmpty(userId)){
            return BaseResponse.build("请登录.");
        }
        return weChatAddressService.addrs(userId);
    }


    @GetMapping("update")
    public BaseResponse updateReceiveAddr(ReceiveAddr receiveAddr){

        if (StringUtils.isEmpty(receiveAddr.getId())){
            return BaseResponse.build("请选择您要修改的地址信息");
        }

        weChatAddressService.updateReceiveAddr(receiveAddr);

        return BaseResponse.build();
    }

    @GetMapping("default")
    public BaseResponse defaultAddr(String userId){

        return weChatAddressService.defaultAddr(userId);

    }

    @GetMapping("remove")
    public BaseResponse removeAddr(String id){
        return weChatAddressService.removeAddr(id);
    }
}
