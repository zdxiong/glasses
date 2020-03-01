package com.xp.glasses.mapper;

import com.xp.glasses.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface ShopMapper extends BaseMapper {
    /**
     * 数量统计
     *
     * @param queryParam
     * @return
     */
    Integer count(Map<String, Object> queryParam);

    void deleteByIds(List<String> ids);

    Shop shopInfoById(String id);
}
