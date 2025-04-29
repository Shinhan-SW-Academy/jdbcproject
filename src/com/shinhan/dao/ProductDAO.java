package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {
    // insert
    public void insertProduct(ProductDTO product) {
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
                values(seq_product.nextval, ?, ?, ?, ?, ?)
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, product.getBusiness_id());
            pst.setString(2, product.getProduct_name());
            pst.setInt(3, product.getProduct_price());
            pst.setString(4, product.getProduct_info());
            pst.setInt(5, product.getProduct_inventory());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, null);
        }
    }

    // update
    public void updateById(ProductDTO product) {
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
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, null);
        }
    }

    // delete
    public void deleteById(String productId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from products where product_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, productId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, null);
        }
    }

    // selectAll
    public List<ProductDTO> selectAll() {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from products order by product_id";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()) {
                ProductDTO product = makeProduct(rs);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, null);
        }
        return productList;
    }

    public List<ProductDTO> selectById(String businessId) {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from products where business_id = ? order by product_id";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            rs = pst.executeQuery();

            while(rs.next()) {
                ProductDTO product = makeProduct(rs);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    // selectByName
    public List<ProductDTO> selectByName(String name) {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from products where product_name like ? order by product_id";

        try {
            pst = conn.prepareStatement(sql);
            name = "%" + name + "%";
            pst.setString(1, name);
            rs = pst.executeQuery();

            while(rs.next()) {
                ProductDTO product = makeProduct(rs);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, null);
        }

        return productList;
    }

    private ProductDTO makeProduct(ResultSet rs) throws SQLException {
        ProductDTO product = ProductDTO.builder()
                .product_id(rs.getString("product_id"))
                .business_id(rs.getString("business_id"))
                .product_name(rs.getString("product_name"))
                .product_price(rs.getInt("product_price"))
                .product_info(rs.getString("product_info"))
                .product_inventory(rs.getInt("product_inventory"))
                .build();

        return product;
    }
}
