package com.pigergod.springbootmall.dao.impl;

import com.pigergod.springbootmall.dao.ProductDao;
import com.pigergod.springbootmall.dto.ProductRequest;
import com.pigergod.springbootmall.model.Product;
import com.pigergod.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ProductDaoImpl
 * Description:
 * Create:2023/4/22 下午 03:41
 */
@Component
public class ProductDaoImpl implements ProductDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductById(Integer productId) {
        String sql = "select  product_id,product_name, category," +
                "image_url, price, stock, description, created_date,"+
                " last_modified_date from product where product_id= :product_Id";

        Map<String, Object> map = new HashMap<>();
        map.put("product_Id", productId);


        //接住query方法的返回值
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

if(productList != null && productList.size() > 0){
            return productList.get(0);}

else{
    return null;
        }
    }



    //寫SQL，去資料庫中創建一個新的商品
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql= "insert into product(product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "values(:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageurl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());


        //創建當下的時間
        Date now= new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        //用keyHolder來接住創建的商品的id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "update product set product_name = :productName, category = :category, image_url = :image_url, price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageurl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("lastModifiedDate", new Date());
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "delete from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}

