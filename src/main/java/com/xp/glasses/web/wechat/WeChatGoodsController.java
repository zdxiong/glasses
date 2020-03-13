package com.xp.glasses.web.wechat;

import com.xp.glasses.service.wechat.WeChatGoodsService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/goods/")
public class WeChatGoodsController {

    @Autowired
    WeChatGoodsService weChatGoodsService;

    /**
     * 爆款推荐 随机获取推荐产品4款
     *
     * @return
     */
    @RequestMapping("recommendGoods")
    public BaseResponse recommendGoods() {
        return weChatGoodsService.recommendGoods();
    }

    /**
     * 获取所有商品
     *
     * @return
     */
    @RequestMapping("allGoods")
    public BaseResponse allGoodsList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return weChatGoodsService.allGoodsList(page, pageSize);
    }

    /**
     * 获取商品详情信息
     * @param goodsId
     * @return
     */
    @RequestMapping("goodsDetail")
    public BaseResponse goodsDetail(@RequestParam String goodsId){

        return weChatGoodsService.goodsDetail(goodsId);
    }

    /**
     * @param cid
     * @return
     */
    @RequestMapping("getByCid")
    public BaseResponse getGoodsByCid(String cid){
        return weChatGoodsService.getGoodsByCid(cid);
    }

}
