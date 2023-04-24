package com.pigergod.springbootmall.dto;

import com.pigergod.springbootmall.constant.ProductCategory;

/**
 * ClassName:ProductQueryParams
 * Description:
 * Create:2023/4/24 下午 12:00
 */
public class ProductQueryParams {
    //做一個DTO，用來接收前端傳過來的參數
    //DTO是Data Transfer Object的縮寫，翻譯成中文就是數據傳輸對象
    private String search;
    private ProductCategory category;

    private String sort;
    private String orderBy;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
