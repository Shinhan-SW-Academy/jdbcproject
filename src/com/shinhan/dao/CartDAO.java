package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.CartDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartDAO {
    // insert
    public int insertCart(CartDTO cart) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = """
                insert into carts(
                cart_id,
                user_id,
                product_id,
                cart_num,
                cart_price)
                values(?, ?, ?, ?,
                       (select product_price
                       from products
                       where product_id = ?)*?)
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cart.getCart_id());
            pst.setString(2, cart.getUser_id());
            pst.setInt(3, cart.getProduct_id());
            pst.setInt(4, cart.getCart_num());
            pst.setInt(5, cart.getProduct_id());
            pst.setInt(6, cart.getCart_num());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // update
    public int updateById(CartDTO cart) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        Map<String, Object> dynamicSQL = new HashMap<>();

        if(cart.getCart_num() != null) dynamicSQL.put("cart_num", cart.getCart_num());

        String sql = "update carts set ";
        String sql2 = " where cart_id = ?";
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
            pst.setInt(i, cart.getCart_id());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // delete
    public int deleteById(Integer cartId) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from carts where cart_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cartId);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
