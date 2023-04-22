package com.pigergod.springbootmall.dao.impl;

import com.pigergod.springbootmall.dao.ProductDao;
import com.pigergod.springbootmall.model.Product;
import com.pigergod.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

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


}

