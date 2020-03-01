package com.xp.glasses.service.impl;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.entity.Image;
import com.xp.glasses.entity.Shop;
import com.xp.glasses.entity.form.ShopInsertForm;
import com.xp.glasses.mapper.ImageMapper;
import com.xp.glasses.mapper.ShopMapper;
import com.xp.glasses.service.ShopService;
import com.xp.glasses.utils.IdUtils;
import com.xp.glasses.utils.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl extends BaseServiceImpl implements ShopService {

    private ImageMapper imageMapper;
    private ServerConfig serverConfig;
    private ShopMapper shopMapper;

    public ShopServiceImpl(ServerConfig serverConfig,
                           ShopMapper shopMapper,
                           ImageMapper imageMapper) {
        super(shopMapper);
        this.serverConfig = serverConfig;
        this.shopMapper = shopMapper;
        this.imageMapper = imageMapper;
    }

    @Override
    public Integer insert(Object o) throws IOException {
        Date createTime = new Date();
        ShopInsertForm shopInsertForm = (ShopInsertForm) o;
        shopInsertForm.setId(IdUtils.initId());
        shopInsertForm.setCreateTime(createTime);
//         StringBuffer urlStr;
//        File file;
//        for (MultipartFile multipartFile : multipartFiles) {
//            String uuid = IdUtils.initId();
//            String original = multipartFile.getOriginalFilename();
//            String suffix = original.substring(original.lastIndexOf("."));
//            Image image = new Image();
//            image.setId(uuid);
//            image.setMappingId(shopInsertForm.getId());
//            image.setType(ImageType.MAIN_SHOP);
//            urlStr = new StringBuffer()
//                    .append(serverConfig.getUrl())
//                    .append(File.separator)
//                    .append(serverConfig.projectName)
//                    .append(File.separator)
//                    .append(uuid)
//                    .append(suffix);
//            image.setUrl(urlStr.toString());
//            image.setSort((byte) 0);
//            images.add(image);
//            file = new File(serverConfig.filePath + File.separator + uuid + suffix);
//            // 上传文件
//            multipartFile.transferTo(file);
//        }

        return super.insert(o);
    }


    @Override
    public PageInfo<Shop> shopListPageQuery(Integer page, Integer pageSize) {
        Integer start = (page - 1) * pageSize;
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("start", start);
        queryParam.put("pageSize", pageSize);
        List<Shop> shops = shopMapper.select(queryParam);
        Integer count = shopMapper.count(queryParam);
        PageInfo pageBean = new PageInfo(pageSize, page, count, shops);
        return pageBean;
    }


    @Override
    public void deleteByIds(List<String> ids) {
        shopMapper.deleteByIds(ids);
    }


    @Override
    public Shop shopInfoById(String id) {
        Shop shop = shopMapper.shopInfoById(id);
        if (Objects.isNull(shop)){
            return null;
        }
        List<Image> images = shop.getImages();
        if (!CollectionUtils.isEmpty(images)){
            for (Image image : shop.getImages()) {
                image.setUrl(serverConfig.imageRealUlr(image.getUrl()));
            }
        }
        return shop;
    }
}
