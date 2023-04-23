package com.pigergod.springbootmall.dto;

import com.pigergod.springbootmall.constant.ProductCategory;
import org.springframework.lang.NonNull;


/**
 * ClassName:ProductRequest
 * Description:
 * Create:2023/4/23 上午 08:45
 */
public class ProductRequest {
   //決定好前端要傳過來的參數，做gettersetter

    @NonNull
    private String productName;
    @NonNull
    private ProductCategory category;
    @NonNull
    private String imageurl;
    @NonNull

    private Integer price;
    @NonNull

    private Integer stock;
    @NonNull

    private String description;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
