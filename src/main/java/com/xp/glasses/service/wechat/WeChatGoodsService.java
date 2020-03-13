package com.xp.glasses.service.wechat;


import com.xp.glasses.utils.BaseResponse;

/**
 * @author Mrxiong
 */
public interface WeChatGoodsService {
    /**
     * 随机获取4条推荐数据·
     * @return
     */
    BaseResponse recommendGoods();

    /**
     * 获取所有的商品列表
     * @param page
     * @param pageSize
     * @return
     */
    BaseResponse allGoodsList(Integer page ,Integer pageSize);

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    BaseResponse goodsDetail(String goodsId);

    /**
     * 根据商品ID查询
     * @param cid
     * @return
     */
    BaseResponse getGoodsByCid(String cid);
}
