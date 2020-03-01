package com.xp.glasses.service;

import com.xp.glasses.entity.Category;

import java.util.List;

/**
 * @author Mrxiong
 */
public interface CategoryService extends BaseService {

    /**
     * 获取所有子分类菜单
     *
     * @return
     */
    List<Category> selectChild();
}
