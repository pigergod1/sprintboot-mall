package com.pigergod.springbootmall.controller;

import com.pigergod.springbootmall.constant.ProductCategory;
import com.pigergod.springbootmall.dto.ProductQueryParams;
import com.pigergod.springbootmall.dto.ProductRequest;
import com.pigergod.springbootmall.model.Product;
import com.pigergod.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName:ProductController
 * Description:
 * Create:2023/4/22 下午 06:16
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //查詢所有商品
    //返回值是一個List<Product>，所以我們可以用ResponseEntity<List>來表示，List裡面放的是Product數據。
    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(

            //表示category的參數是從URL中取得的請求參數。前端就可以靠傳過來的值指定要查詢的類別。
            //我們可以從URL中取得參數，例如：http://localhost:8080/products?category=BOOKS
            //查詢條件 Filtering
            @RequestParam (required = false)ProductCategory category,
            @RequestParam (required = false)String search,

            //在工作當中。一定會想要顯示出最新商品在第一頁上
            //如果沒有前端傳過來的值，就會使用預設值，這裡設定是商品創建的時間
            //排序 Sorting
            @RequestParam (defaultValue = "created_date")String orderBy, //根據哪個欄位排序
            @RequestParam (defaultValue = "desc")String sort //升序或降序
    ){
        //用ProductQueryParams來整理全部的變數，會讓程式碼更簡潔
        //未來只要想新增變數，只要在Controller層新增一個參數，然後在ProductQueryParams裡面新增一個變數就可以了
        //不需要再去改動Service層和Dao層
        //把前端傳來的值set到productQueryParams這個變數裡
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);

        //先把category的值傳到getProducts()方法中，並且會回傳一個List<Product>的列表
        //getProducts的方法沒有參數，所以我們可以直接調用，並會回傳一個List<Product>的列表
        //一路修改interface和class把search傳到Dao層
        List<Product> productList =productService.getProducts(productQueryParams);
        //把productList放到body(responsebody)中，並且回傳給前端
        return ResponseEntity.status(HttpStatus.OK).body(productList);

    }

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


    @PostMapping("/product")
    //RequestBody 代表從前端傳過來的資料會被轉成ProductRequest物件
    //ProductRequest物件是我們自己定義的，用來接收前端傳過來的json參數，並通過我們設定的notnull來驗證
    //只要有@notnull的驗證，一定還要加上@valid--->兩個佩在一起才會生效
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest  productRequest){
    //可以從Dao層實作回來，也可以從Controller層實作進去。
        //我們預期productService會提供一個createProduct的方法，來把productRequest轉成Product物件，會去資料庫中創建商品
        //然後返回資料庫生成的productId給我們
        Integer productId = productService.createProduct(productRequest);

        //從資料庫中查詢一次，確保資料庫中有剛剛創建的商品
        Product product = productService.getProductById(productId);
    //把傳回去的數據，放到body中，並且回傳給前端
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

        //接下來去productService新增createProduct的方法

    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
    //為了更嚴謹，我們可以先去資料庫中查詢，先去確保資料庫中是否有這個商品
    Product product = productService.getProductById(productId);
    if(product == null){
    //如果沒有，就回傳404
    return ResponseEntity.notFound().build();
}
        //開始修改商品數據
        //我們預期productService會提供一個updateProduct的方法，來把productRequest轉成Product物件，會去資料庫中更新商品
        //然後返回資料庫生成的productId給我們
        //productRequest來表示修改過後的值為何
        productService.updateProduct(productId,productRequest);

        //去資料庫中查詢更新過後的值，確保資料庫中有剛剛創建的商品

        Product updateProduct = productService.getProductById(productId);
        //把傳回去的數據，放到body中，並且回傳給前端
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);

        //接下來去productService新增updateProduct的方法
    }


    //刪除商品
    //只要確定商品消失不見，就確定刪除成功，不需要特別設定條件
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
    productService.deleteProductById(productId);

    //刪除成功，返回204
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
