package com.xp.glasses.service;

import com.xp.glasses.entity.form.BannerForm;

import java.io.IOException;
import java.util.Map;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
public interface BannerService extends BaseService {

    /**
     * 添加banner
     *
     * @param bannerForm
     * @return
     * @throws IOException
     */
    void insertBanner(BannerForm bannerForm) throws IOException;

    /**
     * 获取banner总条数
     * @param map
     * @return
     */
    Integer count(Map map);
}
