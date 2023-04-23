package com.pigergod.springbootmall.rowmapper;

import com.pigergod.springbootmall.constant.ProductCategory;
import com.pigergod.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:ProductRowMapper
 * Description:
 * Create:2023/4/22 下午 05:12
 */
public class ProductRowMapper implements RowMapper<Product> {
    //RowMapper是一個介面，裡面只有一個方法
    //mapRow方法，這個方法會在query方法中被調用
    //query方法會把每一行的資料都傳進來
    //我們可以在這個方法中把每一行的資料轉換成一個Product物件

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product(); //先做個product物件
//        可以從resultSet拿到資料庫的資料
        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));


        //因為我們在資料庫中存的是String，但是我們在Product類中定義的是enum
        String categoryStr =  resultSet.getString("category");
        //這邊要把String轉成enum
        ProductCategory category = ProductCategory.valueOf(categoryStr);
        product.setCategory(category);

        product.setImageurl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreateDate(resultSet.getDate("created_date"));
        product.setLastModifiedDate(resultSet.getDate("last_modified_date"));
        return product;
    }
}
