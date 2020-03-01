package com.xp.glasses.web;

import com.xp.glasses.apo.validate.ParamValidated;
import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.Banner;
import com.xp.glasses.entity.ReturnData;
import com.xp.glasses.entity.form.BannerForm;
import com.xp.glasses.service.BannerService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Mrxiong
 * @date 2020/01/11
 */
@Controller
@RequestMapping("/banner/")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    ServerConfig serverConfig;


    @RequestMapping("insert")
    @ParamValidated
    public String insertBanner(BannerForm bannerForm) throws IOException {
        bannerService.insertBanner(bannerForm);

        return "redirect:banners";
    }


    @ResponseBody
    @RequestMapping("all")
    public ReturnData bootstrapTable() {
        // 获取所有的banner图片
        List<Banner> banners = bannerService.select(null);
        banners.forEach(banner -> banner.setUrl(serverConfig.imageRealUlr(banner.getUrl())));
        // 获取行数
        Integer count = bannerService.count(null);
        ReturnData returnData = new ReturnData();
        returnData.setRows(banners);
        returnData.setTotal(count);
        return returnData;

    }

    @RequestMapping("banners")
    public String banners() {

        return "banner";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam String id,@RequestParam String imgName){
        bannerService.deleteById(id);
        File file = new File(serverConfig.filePath+File.separator+imgName);

        if (file.exists()){
            file.delete();
        }
        return "banner";

    }

}
