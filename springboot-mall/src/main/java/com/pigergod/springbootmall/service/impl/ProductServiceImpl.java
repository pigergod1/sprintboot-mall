package com.pigergod.springbootmall.service.impl;

import com.pigergod.springbootmall.dao.ProductDao;
import com.pigergod.springbootmall.model.Product;
import com.pigergod.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:ProductServiceImpl
 * Description:
 * Create:2023/4/22 下午 06:08
 */
@Component
public class ProductServiceImpl implements ProductService {

//注入ProductDao
    @Autowired
    private ProductDao productDao;
    //直接去 call ProductDao的 getProductById 方法
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }


}
