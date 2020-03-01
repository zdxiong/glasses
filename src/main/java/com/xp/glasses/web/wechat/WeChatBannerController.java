package com.xp.glasses.web.wechat;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.entity.Banner;
import com.xp.glasses.service.BannerService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/banner")
public class WeChatBannerController {

    @Autowired
    BannerService bannerService;

    @Autowired
    ServerConfig serverConfig;

    @RequestMapping("banners")
    public BaseResponse banners() {

        List<Banner> select = bannerService.select(null);

        select.forEach(banner -> banner.setUrl(serverConfig.imageRealUlr(banner.getUrl())));

        return BaseResponse.build(select);
    }

}
