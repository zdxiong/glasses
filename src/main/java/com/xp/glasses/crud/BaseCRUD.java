package com.xp.glasses.crud;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 抽取公共的查询方法
 *
 * @author Mrxiong
 * @date 2020/01/10
 */

@SuppressWarnings("all")
public interface BaseCRUD<T> {

    /**
     * 根据ID 查询一条记录
     *
     * @param id
     * @return
     */
    T selectById(String id);

    /**
     * 批量查询记录
     *
     * @param params
     * @return
     */
    List<T> select(Map<String, Object> params);


    /**
     * 添加单条记录
     *
     * @param t
     * @return
     */
    Integer insert(T t) throws IOException;

    /**
     * 批量添加多条记录
     *
     * @param ts
     * @return
     */
    Integer insertMore(List<T> ts);

    /**
     * 修改记录
     *
     * @param t
     * @return
     */
    Integer update(T t) throws IOException;

    /**
     * 根据ID 删除一条记录
     *
     * @param id
     * @return
     */
    Integer deleteById(String id);

    /**
     * 根据指定条件删除记录
     *
     * @param deleteParams
     * @return
     */
    Integer deleteMore(Map<String, Object> deleteParams);

}
