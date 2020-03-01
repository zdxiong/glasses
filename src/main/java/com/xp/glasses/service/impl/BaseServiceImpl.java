package com.xp.glasses.service.impl;

import com.xp.glasses.mapper.BaseMapper;
import com.xp.glasses.service.BaseService;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseMapper<T> baseMapper;

    public BaseServiceImpl(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public T selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> select(Map<String, Object> params) {
        return baseMapper.select(params);
    }

    @Override
    public Integer insert(T t) throws IOException {
        return baseMapper.insert(t);
    }

    @Override
    public Integer insertMore(List<T> list) {
        return baseMapper.insertMore(list);
    }

    @Override
    public Integer update(T t) throws IOException {
        return baseMapper.update(t);
    }

    @Override
    public Integer deleteById(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer deleteMore(Map<String, Object> deleteParams) {
        return baseMapper.deleteMore(deleteParams);
    }
}
