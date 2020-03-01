package com.xp.glasses.mapper;

import com.xp.glasses.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper {
    /**
     * 获取所有子分类
     * @return
     */
    List<Category> selectChild();
}
