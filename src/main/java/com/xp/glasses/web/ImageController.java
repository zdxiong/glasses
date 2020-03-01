package com.xp.glasses.web;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ImageType;
import com.xp.glasses.entity.Image;
import com.xp.glasses.entity.ReturnData;
import com.xp.glasses.service.ImageService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mrxiong
 */
@Controller
@RequestMapping("/img/")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    ServerConfig serverConfig;

    /**
     * 添加图片信息
     *
     * @param mappingId 映射实体的ID
     * @param img       图片信息
     * @param type      图片类型
     * @param sort      排列序号
     * @return
     */
    @RequestMapping("insert")
    public String insertImage(@RequestParam String mappingId, @RequestParam MultipartFile img,
                              @RequestParam ImageType type, @RequestParam Byte sort) throws IOException {

        imageService.saveImage(mappingId, img, type, sort);

        if (type.equals(ImageType.MAIN_SHOP)) {
            return "redirect:/shop/myShop";
        }
        if (type.equals(ImageType.INFO_GOODS)) {
            return "redirect:/goods/myGoods";
        }
        return "redirect:/";
    }


    @RequestMapping("delImg")
    public String deleteImage(@RequestParam String id, @RequestParam ImageType type) {
        Image image = (Image) imageService.selectById(id);
        imageService.deleteById(id);
        String name = image.getUrl();
        String filePath = serverConfig.filePath;
        File file = new File(filePath + File.separator + name);
        if (file.exists()) {
            file.delete();
        }
        imageService.deleteById(id);
        if (type.equals(ImageType.MAIN_SHOP)) {
            return "redirect:/shop/myShop";
        }
        if (type.equals(ImageType.INFO_GOODS)) {
            return "redirect:/goods/myGoods";
        }
        return "redirect:/";
    }

    @RequestMapping("get")
    @ResponseBody
    public BaseResponse getImage(@RequestParam String mappingId, @RequestParam ImageType type) {
        Map<String, String> queryParam = new HashMap<>(2);
        queryParam.put("mappingId", mappingId);
        queryParam.put("type", type.name());
        List<Image> images = imageService.select(queryParam);
        images.forEach(image -> image.setUrl(serverConfig.imageRealUlr(image.getUrl())));
        return BaseResponse.build(images);
    }


    @RequestMapping("get1")
    @ResponseBody
    public ReturnData getImage1(@RequestParam String mappingId, @RequestParam ImageType type) {
        Map<String, String> queryParam = new HashMap<>(2);
        queryParam.put("mappingId", mappingId);
        queryParam.put("type", type.name());
        List<Image> images = imageService.select(queryParam);
        images.forEach(image -> image.setUrl(serverConfig.imageRealUlr(image.getUrl())));

        ReturnData returnData = new ReturnData();
        returnData.setRows(images);
        return returnData;
    }

}
