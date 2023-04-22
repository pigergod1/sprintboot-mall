package com.pigergod.springbootmall.controller;

import com.pigergod.springbootmall.model.Product;
import com.pigergod.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ProductController
 * Description:
 * Create:2023/4/22 下午 06:16
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

@GetMapping("/product/{productId}")
//ResponseEntity<Product> 代表返回的是一個Product物件
//@PathVariable 代表從URL路徑中取得productId
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
       Product product = productService.getProductById(productId);

       //如果product不是null，就回傳一個200的狀態碼，並且把product物件回傳
    //body()方法是把product物件放到body中，product物件會被轉成json格式
    //product是查詢資料庫出來的數據，並且是一個物件
       if(product != null){
           return ResponseEntity.status(HttpStatus.OK).body(product);
       }
       else{

           //build()方法是建立一個ResponseEntity物件，並且回傳給前端
           return ResponseEntity.notFound().build();
       }
    }
}
