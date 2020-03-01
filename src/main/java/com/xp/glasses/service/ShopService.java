package com.xp.glasses.service;

import com.xp.glasses.entity.Shop;
import com.xp.glasses.utils.PageInfo;

import java.util.List;

/**
 * @author Mrxiong
 */
public interface ShopService extends BaseService {

    /**
     * 分页查询数据
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Shop> shopListPageQuery(Integer page, Integer pageSize);

    /**
     * 根据ID集合删除记录
     * @param ids
     */
    void deleteByIds(List<String> ids);

    /**
     * 查询门店信息，包含图片
     * @param id
     * @return
     */
    Shop shopInfoById(String id);
}
