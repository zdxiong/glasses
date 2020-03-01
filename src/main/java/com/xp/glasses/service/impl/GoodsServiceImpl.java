package com.xp.glasses.service.impl;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ImageType;
import com.xp.glasses.entity.*;
import com.xp.glasses.entity.form.GoodsForm;
import com.xp.glasses.mapper.GoodsMapper;
import com.xp.glasses.service.GoodsService;
import com.xp.glasses.service.ImageService;
import com.xp.glasses.utils.IdUtils;
import com.xp.glasses.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    ServerConfig serverConfig;

    @Autowired
    ImageService imageService;

    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        super(goodsMapper);
        this.goodsMapper = goodsMapper;
    }


    @Override
    public ReturnData bootstrapTableData(GoodsQueryParam goodsQueryParam) {
        Map<String, Object> param = new HashMap<>(2);

        if (goodsQueryParam.getCurrentPage() == null){
            goodsQueryParam.setCurrentPage(1);
        }
        if (goodsQueryParam.getPageSize() == null){
            goodsQueryParam.setPageSize(5);
        }
        param.put("offset", (goodsQueryParam.getCurrentPage()-1)*goodsQueryParam.getPageSize());
        param.put("limit", goodsQueryParam.getPageSize());
        if (!StringUtils.isEmpty(goodsQueryParam.getShopId())){
            param.put("shopId", goodsQueryParam.getShopId());
        }
        if (!StringUtils.isEmpty(goodsQueryParam.getCategoryId())){
            param.put("categoryId", goodsQueryParam.getCategoryId());
        }
        List<Goods> goodsList = goodsMapper.select(param);
        for (Goods goods : goodsList) {
            if (goods.getDiscountsPrice() != null) {
                goods.setDiscountsPrice(goods.getDiscountsPrice() / 100);
            }
            if (goods.getMarketPrice() != null) {
                goods.setMarketPrice(goods.getMarketPrice() / 100);
            }
            if (goods.getNormalPrice() != null) {
                goods.setNormalPrice(goods.getNormalPrice() / 100);
            }
            goods.getMainImage().setUrl(serverConfig.imageRealUlr(goods.getMainImage().getUrl()));

        }
        Integer count = goodsMapper.count(param);
        ReturnData returnData = new ReturnData();
        returnData.setTotal(count);
        returnData.setRows(goodsList);
        return returnData;
    }

    @Override
    public List<GoodsSpecification> specifications(String goodsId) {
        List<GoodsSpecification> specifications = goodsMapper.specifications(goodsId);
        for (GoodsSpecification specification : specifications) {
            Image image = specification.getImg();
            if (!Objects.isNull(image)) {
                specification.setImageUrl(serverConfig.imageRealUlr(image.getUrl()));
            }
        }
        return goodsMapper.specifications(goodsId);
    }

    @Override
    public Integer insert(Object o) throws IOException {
        GoodsForm goodsForm = (GoodsForm) o;
        goodsForm.setId(IdUtils.initId());
        goodsForm.setCreateTime(new Date());
        // 价格收到的是元。存入数据库存成分
        goodsForm.setNormalPrice(goodsForm.getNormalPrice() * 100);
        if (goodsForm.getDiscountsPrice() != null) {
            goodsForm.setDiscountsPrice(goodsForm.getDiscountsPrice() * 100);
        }
        goodsForm.setMarketPrice(goodsForm.getMarketPrice() * 100);
        MultipartFile image = goodsForm.getImage();
        String randomName = ImageUtils.randomName(image);
        Image imageDto = new Image();
        imageDto.setId(IdUtils.initId());
        imageDto.setUrl(randomName);
        imageDto.setMappingId(goodsForm.getId());
        imageDto.setType(ImageType.MAIN_GOODS);

        super.insert(goodsForm);
        Integer res = imageService.insert(imageDto);

        image.transferTo(new File(serverConfig.filePath + File.separator + randomName));

        return res;
    }

    @Override
    public Integer update(Object o) throws IOException {
        GoodsForm goodsForm = (GoodsForm) o;

        if (goodsForm.getDiscountsPrice() != null) {
            goodsForm.setDiscountsPrice(goodsForm.getDiscountsPrice() * 100);
        }

        if (goodsForm.getMarketPrice() != null) {
            goodsForm.setMarketPrice(goodsForm.getMarketPrice() * 100);
        }

        if (goodsForm.getNormalPrice() != null) {
            goodsForm.setNormalPrice(goodsForm.getNormalPrice() * 100);
        }

        String goodsId = goodsForm.getId();
        MultipartFile image = goodsForm.getImage();
        String newUrl = null;
        String oldUrl = null;
        // 有图片修改
        if (image != null && image.getInputStream() != null) {
            List<Image> imageList = getImage(goodsId, ImageType.MAIN_GOODS);
            Image image1 = imageList.get(0);
            oldUrl = image1.getUrl();
            newUrl = ImageUtils.randomName(image);
            image1.setUrl(newUrl);
            imageService.update(image1);
        }
        Integer update = super.update(o);
        if (image != null && image.getInputStream() != null) {
            File file = new File(serverConfig.filePath + "/" + newUrl);
            image.transferTo(file);
            File oldF = new File(serverConfig.filePath + File.separator + oldUrl);
            if (oldF.exists()) {
                oldF.delete();
            }
        }
        return update;
    }

    @Override
    public Integer deleteById(String id) {
        File file = null;
        List<Image> imageList = getImage(id, ImageType.MAIN_GOODS);
        if (!CollectionUtils.isEmpty(imageList)) {
            Image image = imageList.get(0);
            file = new File(serverConfig.filePath + File.separator + image.getUrl());
            // 删除主图
            imageService.deleteById(image.getId());

            // todo 删除规格信息

            // todo 删除规格图片
        }
        // 删除goods信息
        Integer integer = super.deleteById(id);

        // 删除图片
        if (!Objects.isNull(file)) {
            if (file.exists()) {
                file.delete();
            }
        }
        return integer;
    }


    public List<Image> getImage(String mappingId, ImageType type) {
        Map<String, String> params = new HashMap<>(2);
        params.put("type", type.name());
        params.put("mappingId", mappingId);
        return imageService.select(params);
    }

    @Override
    public List<Goods> all() {
        return goodsMapper.all();
    }
}
