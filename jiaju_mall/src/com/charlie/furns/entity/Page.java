package com.charlie.furns.entity;

import java.util.List;

/**
 * Page是一个JavaBean，是一个分页的数据模型，包含了分页的各种信息
 * T表示泛型，因为将来分页模型对应的数据类型是不确定的
 */
public class Page<T> {

    // 因为每页显示多少条记录，在其它地方有可能使用
    // ctrl+shift+u => 切换变量名大小写
    public static final Integer PAGE_SIZE = 3;

    // 表示显示当前页[显示第几页]
    private Integer pageNo;     // 前端页面获取
    // 表示每页显示几条记录
    private Integer pageSize = PAGE_SIZE;
    // 表示共有多少页
    private Integer pageTotalCount; // 通过计算而来
    // 表示的是共有多少条记录
    private Integer totalRow;   // 可以从数据库获得 -> DAO
    // 返回当前页要显示的数据
    private List<T> items;      // 从数据库中获取 -> DAO
    // 分页导航的字符串
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
