package com.xp.glasses.mapper;

import com.xp.glasses.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Mrxiong
 * @date 2020/01/11
 */
@Repository
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    /**
     * 获取banner记录数
     * @param map
     * @return
     */
    Integer count(Map map);
}
