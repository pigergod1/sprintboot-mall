package com.pigergod.springbootmall.service;

import com.pigergod.springbootmall.dto.ProductRequest;
import com.pigergod.springbootmall.model.Product;

import java.util.List;

/**
 * ClassName:ProductService
 * Description:
 * Create:2023/4/22 下午 06:07
 */
public interface ProductService {
    //查詢所有商品
    List<Product> getProducts();

    //直接複製ProductDao.java的方法
    Product getProductById(Integer productId);

    //新增一個方法，用來創建商品
    //返回值是Integer，因為我們預期會返回一個productId給前端
    //定義完方法後，去ProductserviceImpl.java實作
    Integer createProduct(ProductRequest productRequest);

    //因為沒有返回值，所以不用寫return
    void updateProduct(Integer productId, ProductRequest productRequest);

    //沒有返回值
    void deleteProductById(Integer productId);


}
