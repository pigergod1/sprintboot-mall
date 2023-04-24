package com.pigergod.springbootmall.dao;

import com.pigergod.springbootmall.constant.ProductCategory;
import com.pigergod.springbootmall.dto.ProductRequest;
import com.pigergod.springbootmall.model.Product;

import java.util.List;

/**
 * ClassName:ProductDao
 * Description:
 * Create:2023/4/22 下午 03:41
 */
public interface ProductDao {

    //查詢所有商品
    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    //返回值是Integer，因為我們預期會返回一個productId給前端
    //參數是productRequest
    Integer createProduct(ProductRequest productRequest);


    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
