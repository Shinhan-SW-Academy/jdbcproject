package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductDAO {
    // insert
    public int insertProduct(ProductDTO product) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = """
                insert into products(
                product_id,
                business_id,
                product_name,
                product_price,
                product_info,
                product_inventory)
                values(?, ?, ?, ?, ?, ?)
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, product.getProduct_id());
            pst.setString(2, product.getBusiness_id());
            pst.setString(3, product.getProduct_name());
            pst.setInt(4, product.getProduct_price());
            pst.setString(5, product.getProduct_info());
            pst.setInt(6, product.getProduct_inventory());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // update
    public int updateById(ProductDTO product) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        Map<String, Object> dynamicSQL = new HashMap<>();

        if(product.getProduct_name() != null) dynamicSQL.put("product_name", product.getProduct_name());
        if(product.getProduct_price() != null) dynamicSQL.put("product_price", product.getProduct_price());
        if(product.getProduct_info() != null) dynamicSQL.put("product_info", product.getProduct_info());
        if(product.getProduct_inventory() != null) dynamicSQL.put("product_inventory", product.getProduct_inventory());

        String sql = "update products set ";
        String sql2 = " where product_id = ?";
        for(String key : dynamicSQL.keySet()) {
            sql += key + " = ?, ";
        }
        sql = sql.substring(0, sql.length()-1);
        sql += sql2;

        try {
            pst = conn.prepareStatement(sql);
            int i = 1;
            for(String key : dynamicSQL.keySet()) {
                pst.setObject(i++, dynamicSQL.get(key));
            }
            pst.setString(i, product.getProduct_id());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // delete
    public int deleteById(String productId) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from products where product_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, productId);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // selectAll
    // selectByName
    // selectByBusiness
}
