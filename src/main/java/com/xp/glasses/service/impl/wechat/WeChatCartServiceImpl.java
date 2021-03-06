package com.xp.glasses.service.impl.wechat;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.Goods;
import com.xp.glasses.entity.GoodsSpecification;
import com.xp.glasses.entity.Image;
import com.xp.glasses.entity.car.*;
import com.xp.glasses.mapper.wechat.WeChatCartMapper;
import com.xp.glasses.service.GoodsService;
import com.xp.glasses.service.SpecificationService;
import com.xp.glasses.service.UserService;
import com.xp.glasses.service.wechat.WeChatCartService;
import com.xp.glasses.utils.BaseResponse;
import com.xp.glasses.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatCartServiceImpl implements WeChatCartService {

    @Autowired
    WeChatCartMapper weChatCartMapper;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    SpecificationService specificationService;

    @Autowired
    ServerConfig serverConfig;

    @Override
    public BaseResponse joinCart(JoinBuyCarForm joinBuyCarForm) {

        Date nowTime = new Date();
        String goodsId = joinBuyCarForm.getGoodsId();
        HashMap<Object, Object> hashMap = new HashMap<>(1);
        hashMap.put("goodsId", goodsId);
        List<Goods> goodsList = goodsService.select(hashMap);
        if (CollectionUtils.isEmpty(goodsList) || goodsList.size() > 1) {
            return BaseResponse.build("商品未找到");
        }
        Goods goods = goodsList.get(0);
        String userId = joinBuyCarForm.getUserId();

        if (userService.selectById(userId) == null) {
            return BaseResponse.build("非法用户");
        }

        // 用户选择的商品规格,并去重
        List<String> spes = joinBuyCarForm.getSpes().stream().distinct().collect(Collectors.toList());

        // 获取该商品的所有规格,保证每种规格都被选择
        List<GoodsSpecification> goodsSpes = specificationService.getGoodsSpes(goodsId);
        Map<String, List<GoodsSpecification>> speGroup = goodsSpes.stream().
                collect(Collectors.groupingBy(GoodsSpecification::getName));
        boolean flag = false;

        for (String speName : speGroup.keySet()) {
            List<GoodsSpecification> specifications = speGroup.get(speName);
            List<String> allSpeIds = specifications.stream().map(GoodsSpecification::getId)
                    .collect(Collectors.toList());
            for (String specificationId : allSpeIds) {
                if (spes.contains(specificationId)) {
                    flag = true;
                    continue;
                }
            }
            if (!flag) {
                return BaseResponse.build(ResponseCode.INVALID_PARAMS, "请选择商品:" + goods.getName() + "的" + speName);
            }
            flag = false;
        }

        String buyCarId = IdUtils.initId();
        BuyCar buyCar = new BuyCar();
        buyCar.setId(buyCarId);
        buyCar.setChoose(1);
        buyCar.setCreateTime(nowTime);
        buyCar.setUpdateTime(nowTime);
        buyCar.setGoodsId(goodsId);
        buyCar.setNum(joinBuyCarForm.getNum());
        buyCar.setUserId(userId);
        buyCar.setStatus(1);

        List<BuyCarItem> buyCarItems = new ArrayList<>();
        BuyCarItem buyCarItem;
        if (!CollectionUtils.isEmpty(spes)) {
            for (String speId : spes) {
                buyCarItem = new BuyCarItem();
                buyCarItem.setCarId(buyCarId);
                buyCarItem.setSpeId(speId);
                buyCarItems.add(buyCarItem);
            }
        }
        // 找到该用户加入的商品是否已近在购物车存在，
        // 如果存在则比对规格项目，如果规格相同，只需要更改商品的数量
        // 如果不同,则添加新的购物车项目

        // 已近加入到购物车的同类商品
        List<BuyCar> addedBuyCars = weChatCartMapper.getUserCartGoods(userId, goodsId);

        for (BuyCar addedBuyCar : addedBuyCars) {
            List<BuyCarItem> carItems = addedBuyCar.getBuyCarItems();
            List<String> selectedSpes = carItems.stream().map(BuyCarItem::getSpeId)
                    .collect(Collectors.toList());
            if (selectedSpes.containsAll(spes)) {
                addedBuyCar.setNum(addedBuyCar.getNum() + joinBuyCarForm.getNum());
                addedBuyCar.setUpdateTime(nowTime);
                weChatCartMapper.updateBuyCart(addedBuyCar);
                return BaseResponse.build();
            }
        }
        weChatCartMapper.joinCart(buyCar);
        if (!CollectionUtils.isEmpty(buyCarItems)) {
            weChatCartMapper.joinCartItems(buyCarItems);
        }
        return BaseResponse.build();

    }


    @Override
    public BaseResponse cartGoods(String userId) {
        if (userService.selectById(userId) == null) {
            return BaseResponse.build("非法用户");
        }
        List<BuyCar> carGoodsList = weChatCartMapper.cartGoods(userId);
        StringBuffer attrBuffer;
        // 价格
        Long basePrice = 0L;
        Long attachPrice = 0L;
        for (BuyCar buyCar : carGoodsList) {
            attrBuffer = new StringBuffer();
            List<GoodsSpecification> spes = buyCar.getSpes();
            for (GoodsSpecification spe : spes) {
                // 设置属性
                attrBuffer.append(spe.getValue() + "/");
                attachPrice += spe.getAttachPrice();
            }
            buyCar.setSpeAttr(attrBuffer.toString());
            Image mainImage = buyCar.getGoods().getMainImage();
            mainImage.setUrl(serverConfig.imageRealUlr(mainImage.getUrl()));
            Goods goods = buyCar.getGoods();
            Integer promotion = goods.getPromotion();

            if (promotion == 1) {
                if (goods.getDiscountsPrice() == null) {
                    return BaseResponse.build("系统错误");
                }
                basePrice = goods.getDiscountsPrice();
            }

            if (promotion == 0) {
                if (goods.getNormalPrice() == null) {
                    return BaseResponse.build("系统错误");
                }
                basePrice = goods.getNormalPrice();
            }
            // 单价
            buyCar.setUnitPrice(basePrice + attachPrice);
            basePrice = 0L;
            attachPrice = 0L;
        }
        // 设置总价
        Long totalPrice = 0L;
        Map<Integer, List<BuyCar>> chooseMap = carGoodsList.stream().collect(Collectors.groupingBy(BuyCar::getChoose));
        List<BuyCar> chooseGoods = chooseMap.get(1);
        if (!CollectionUtils.isEmpty(chooseGoods)){
            for (BuyCar good : chooseGoods) {
                totalPrice += good.getNum() * good.getUnitPrice();
            }
        }
        BuyCarData buyCarData = new BuyCarData();
        buyCarData.setTotalPrice(totalPrice);
        buyCarData.setCarGoodsList(carGoodsList);
        return BaseResponse.build(buyCarData);
    }

    @Override
    public BaseResponse modifyBuycar(ModifyCarParam carParam) {
        weChatCartMapper.modifyBuycar(carParam);
        return BaseResponse.build();
    }

    @Override
    public BaseResponse deleteBuycar(String id) {
        weChatCartMapper.deleteBuycar(id);
        return BaseResponse.build();
    }
}
