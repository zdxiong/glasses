package com.xp.glasses.service.impl.wechat;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsSpecification;
import com.xp.glasses.entity.Image;
import com.xp.glasses.entity.Shop;
import com.xp.glasses.mapper.wechat.WeChatGoodsMapper;
import com.xp.glasses.service.ImageService;
import com.xp.glasses.service.ShopService;
import com.xp.glasses.service.SpecificationService;
import com.xp.glasses.service.wechat.WeChatGoodsService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatGoodsServiceImpl implements WeChatGoodsService {
    WeChatGoodsMapper weChatGoodsMapper;
    SpecificationService specificationService;
    ServerConfig serverConfig;
    ShopService shopService;
    ImageService imageService;

    public WeChatGoodsServiceImpl(WeChatGoodsMapper weChatGoodsMapper,
                                  SpecificationService specificationService,
                                  ServerConfig serverConfig,
                                  ShopService shopService,
                                  ImageService imageService) {
        this.weChatGoodsMapper = weChatGoodsMapper;
        this.specificationService = specificationService;
        this.serverConfig = serverConfig;
        this.shopService = shopService;
        this.imageService = imageService;
    }


    @Override
    public BaseResponse recommendGoods() {

        List<Goods> weGoods = weChatGoodsMapper.recommendGoods();

        changeGoodsAttr(weGoods);
        return BaseResponse.build(weGoods);
    }

    @Override
    public BaseResponse allGoodsList(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<Goods> goods = weChatGoodsMapper.allGoodsList();
        changeGoodsAttr(goods);

        PageInfo pageInfo = new PageInfo(goods);
        return BaseResponse.build(pageInfo);
    }


    List<Goods> changeGoodsAttr(List<Goods> goods) {


        for (Goods weGood : goods) {
            Image mainImage = weGood.getMainImage();
            mainImage.setUrl(serverConfig.imageRealUlr(mainImage.getUrl()));
            if (weGood.getMarketPrice() != null) {
                weGood.setMarketPrice(weGood.getMarketPrice() / 100);
            }
            if (weGood.getNormalPrice() != null) {
                weGood.setNormalPrice(weGood.getNormalPrice() / 100);
            }
            if (weGood.getDiscountsPrice() != null) {
                weGood.setDiscountsPrice(weGood.getDiscountsPrice() / 100);
            }
        }
        return goods;
    }

    @Override
    public BaseResponse goodsDetail(String goodsId) {
        // 商品信息
        Goods goods = weChatGoodsMapper.goodsDetail(goodsId);


        // 门店信息
        goods.setShop((Shop) shopService.selectById(goods.getShop().getId()));

        //详情图
        Map<String, Object> queryParam = new HashMap<>(2);
        queryParam.put("mappingId", goodsId);
        queryParam.put("type", "INFO_GOODS");
        List<Image> infoImages = imageService.select(queryParam);
        goods.setInfoImages(infoImages);

        // 商品规格
        List<GoodsSpecification> specifications = specificationService.getGoodsSpes(goodsId);
        // 根据名称为规格分组
        Map<String, List<GoodsSpecification>> speMap = specifications.stream()
                .collect(Collectors.groupingBy(goodsSpecification -> goodsSpecification.getName()));
        goods.setSpeMap(speMap);

        // 基础价格
        Long basePrice = 0L;

        Integer promotion = goods.getPromotion();
        // 促销产品
        if (promotion == 1) {
            basePrice = goods.getDiscountsPrice();
        }
        if (promotion == 0) {
            basePrice = goods.getNormalPrice();
        }

        // 最小价格
        Long minPrice = basePrice.longValue();

        // 最大价格
        Long maxPrice = basePrice.longValue();

        for (String speName : speMap.keySet()) {
            List<GoodsSpecification> goodsSpecifications = speMap.get(speName);
            // 获取该规格组中最大的值
            Optional<GoodsSpecification> maxOptional = goodsSpecifications.stream()
                    .max(Comparator.comparingInt(GoodsSpecification::getAttachPrice));
            if (maxOptional.isPresent()) {
                GoodsSpecification goodsSpecification = maxOptional.get();
                Integer attachPrice = goodsSpecification.getAttachPrice();
                // 最大价值 +
                maxPrice += attachPrice;
            }

            // 获取该规格组中最小的值
            Optional<GoodsSpecification> minOptional = goodsSpecifications.stream()
                    .min(Comparator.comparingInt(GoodsSpecification::getAttachPrice));
            if (minOptional.isPresent()) {
                GoodsSpecification goodsSpecification = minOptional.get();
                Integer attachPrice = goodsSpecification.getAttachPrice();
                minPrice += attachPrice;
            }

        }

        goods.setMinPrice(Double.valueOf(minPrice / 100));

        goods.setMaxPrice(Double.valueOf(maxPrice / 100));


        // 为图片添加真实访问路径
        goods.getMainImage().setUrl(serverConfig.imageRealUlr(goods.getMainImage().getUrl()));

        for (Image infoImage : goods.getInfoImages()) {
            infoImage.setUrl(serverConfig.imageRealUlr(infoImage.getUrl()));
        }
        return BaseResponse.build(goods);
    }

    @Override
    public BaseResponse getGoodsByCid(String cid) {
        List<Goods>  goods = weChatGoodsMapper.getGoodsByCid( cid);

        for (Goods good : goods) {
            if (good.getNormalPrice()!= null){
                good.setNormalPrice(good.getNormalPrice()/100);
            }

            if (good.getDiscountsPrice()!=null){
                good.setDiscountsPrice(good.getDiscountsPrice()/100);
            }

            if (good.getMarketPrice()!=null){
                good.setMarketPrice(good.getMarketPrice()/100);
            }

            Image mainImage = good.getMainImage();
            mainImage.setUrl(serverConfig.imageRealUlr(mainImage.getUrl()));
        }
        return BaseResponse.build(goods);
    }
}
