package com.xp.glasses.web.wechat;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.Image;
import com.xp.glasses.entity.Shop;
import com.xp.glasses.mapper.ShopMapper;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/shop/")
public class WeChatShopController {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    ServerConfig serverConfig;

    @GetMapping
    public BaseResponse shopInfo(){

        List<Shop> shops = shopMapper.getOne();

        if (CollectionUtils.isEmpty(shops)){
            return BaseResponse.build(ResponseCode.FAIL,"为获取到门店信息");
        }
        Shop shop = shops.get(0);

        for (Image image : shop.getImages()) {
            image.setUrl(serverConfig.imageRealUlr(image.getUrl()));
        }

        return BaseResponse.build(shop);

    }
}
