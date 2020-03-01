package com.xp.glasses.utils;

import lombok.Data;

import java.util.List;

/**
 * @author xiongzhendong
 * Created by pz on 2019/3/21.
 * 分页工具类
 */
@Data
public class PageInfo<T> {
    /**
     * 每页数据量
     */
    private Integer size;
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 总数居量
     */
    private Integer count;
    /**
     * 数据
     */
    private List<T> aaData;
    /**
     * 总页数
     */
    private Integer totalPate;
    /**
     * 是否第一页
     */
    private Boolean first;
    /**
     * 是否最后一页
     */
    private Boolean last;

    /**
     * @param size
     * @param page
     * @param count
     * @param aaData
     */
    public PageInfo(Integer size, Integer page, Integer count, List<T> aaData) {
        this.size = size;
        this.page = page;
        this.count = count;
        this.aaData = aaData;
        // 计算总页数
        this.totalPate = (count + (size - 1)) / size;
        if (page == totalPate)
            this.last = true;
        else
            this.last = false;

        if (page == 1)
            this.first = true;
        else
            this.first = false;

        if (count == 0){
            first =false;
            last = false;
        }


    }

    public Integer getSize() {
        return size;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getCount() {
        return count;
    }

    public List<T> getData() {
        return aaData;
    }

    public Integer getTotalPate() {
        return totalPate;
    }

    public Boolean getFirst() {
        return first;
    }

    public Boolean getLast() {
        return last;
    }
}
