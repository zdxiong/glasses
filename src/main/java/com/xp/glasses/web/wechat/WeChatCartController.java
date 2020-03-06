package com.xp.glasses.web.wechat;

import com.xp.glasses.entity.car.BuyCar;
import com.xp.glasses.entity.car.JoinBuyCarForm;
import com.xp.glasses.entity.car.ModifyCarParam;
import com.xp.glasses.service.wechat.WeChatCartService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/cart/")
public class WeChatCartController {

    @Autowired
    WeChatCartService weChatCartService;

    /**
     * 加入购物车
     *
     * @param joinBuyCarForm
     * @return
     */
    @RequestMapping("joinBuyCart")
    public BaseResponse joinCart(@RequestBody JoinBuyCarForm joinBuyCarForm) {

        if (Objects.isNull(joinBuyCarForm)) {
            return BaseResponse.build("参数错误");
        }

        if (StringUtils.isEmpty(joinBuyCarForm.getUserId())) {
            return BaseResponse.build("请登录");
        }

        if (StringUtils.isEmpty(joinBuyCarForm.getGoodsId())) {
            return BaseResponse.build("请选择商品");
        }

        if (joinBuyCarForm.getNum() == null) {
            return BaseResponse.build("请选择商品数量");
        }

        return weChatCartService.joinCart(joinBuyCarForm);

    }

    @RequestMapping("cartGoods")
    public BaseResponse cartGoods(@RequestParam String userId) {

        if (StringUtils.isEmpty(userId)) {
            return BaseResponse.build("请登录");
        }
        return weChatCartService.cartGoods(userId);
    }


    @RequestMapping("modifyBuycar")
    public BaseResponse modifyBuycar(ModifyCarParam carParam) {

        if (Objects.isNull(carParam)) {
            return BaseResponse.build("参数错误");
        }

        if (carParam.getChoose() == null && carParam.getNum() == null) {
            return BaseResponse.build("参数错误");
        }

        return weChatCartService.modifyBuycar(carParam);
    }


    @RequestMapping("deleteBuycar")
    public BaseResponse deleteBuycar(String id){

        if (StringUtils.isEmpty(id)){
            return BaseResponse.build("请选择要删除的商品");
        }

        return weChatCartService.deleteBuycar(id);

    }

}
