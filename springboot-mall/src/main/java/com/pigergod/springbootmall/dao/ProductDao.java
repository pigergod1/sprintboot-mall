package com.pigergod.springbootmall.dao;

import com.pigergod.springbootmall.model.Product;

/**
 * ClassName:ProductDao
 * Description:
 * Create:2023/4/22 下午 03:41
 */
public interface ProductDao {

    Product getProductById(Integer productId);
}
