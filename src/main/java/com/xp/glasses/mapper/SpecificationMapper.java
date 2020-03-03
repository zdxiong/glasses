package com.xp.glasses.mapper;

import com.xp.glasses.entity.GoodsSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface SpecificationMapper extends BaseMapper {
    /**
     * 获取商品规格
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> getGoodsSpes(String goodsId);

    /**
     * 根据规格ID集合获取多个规格
     * @param speIds
     * @return
     */
    List<GoodsSpecification> selectBySpeIds(List<String> speIds);
}
