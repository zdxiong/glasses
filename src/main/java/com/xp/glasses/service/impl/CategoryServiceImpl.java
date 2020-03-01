package com.xp.glasses.service.impl;

import com.xp.glasses.entity.Category;
import com.xp.glasses.mapper.CategoryMapper;
import com.xp.glasses.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        super(categoryMapper);
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> selectChild() {
        return categoryMapper.selectChild();
    }
}
