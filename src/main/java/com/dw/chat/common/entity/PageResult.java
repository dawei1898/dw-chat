package com.dw.chat.common.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果封装对象
 *
 * @author dawei
 */
@Data
public class PageResult<T> implements Serializable{

    /**
     * 页码，从1开始
     */
    @JSONField(ordinal = 1)
    private Integer pageNum;

    /**
     * 每页数据条数
     */
    @JSONField(ordinal = 2)
    private Integer pageSize;

    /**
     * 总页数
     */
    @JSONField(ordinal = 3)
    private Integer pages;

    /**
     * 总记录数
     */
    @JSONField(ordinal = 4)
    private long total;

    /**
     * 当前页结果
     */
    @JSONField(ordinal = 5)
    private List<T> list;




    public PageResult() {
    }

    public PageResult(List<T> rows) {
        this.list = rows;
    }

    public PageResult(long total, int pages, List<T> rows) {
        this.total = total;
        this.pages = pages;
        this.list = rows;
    }

    public PageResult(int pageNum, int pageSize, long total, int pages, List<T> rows) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.list = rows;
    }

    public static  <T> PageResult<T>  build() {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(new ArrayList<>());
        return pageResult;
    }

    public static <T> PageResult<T> build(Integer pageNum, Integer pageSize, Integer total, List<T> rows) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(rows);
        pageResult.setPageNum(pageNum);
        pageResult.setTotal(total);
        if (pageNum == 0) {
            pageResult.setPageSize(total);
            pageResult.setPages(1);
        } else {
            pageResult.setPageSize(pageSize);
            pageResult.setPages((int)Math.round(Math.ceil((double) total / (double) pageSize)));
        }
        return pageResult;
    }

}
