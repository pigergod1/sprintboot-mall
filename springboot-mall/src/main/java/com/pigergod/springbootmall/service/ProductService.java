package com.pigergod.springbootmall.service;

import com.pigergod.springbootmall.model.Product;

/**
 * ClassName:ProductService
 * Description:
 * Create:2023/4/22 下午 06:07
 */
public interface ProductService {

//直接複製ProductDao.java的方法
    Product getProductById(Integer productId);
}
