package com.xp.glasses.mapper;


import com.xp.glasses.crud.BaseCRUD;
import org.apache.ibatis.annotations.Mapper;

/**
 * baseMapper
 * @author Mrxiong
 * @param <T>
 */
@Mapper
public interface BaseMapper<T> extends BaseCRUD<T> {
}
