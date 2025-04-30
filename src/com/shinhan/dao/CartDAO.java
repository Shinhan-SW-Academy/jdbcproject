package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.CartDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                values(seq_cart.nextval, ?, ?, ?,
                       (select product_price
                       from products
                       where product_id = ?)*?)
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, cart.getUser_id());
            pst.setInt(2, cart.getProduct_id());
            pst.setInt(3, cart.getCart_num());
            pst.setInt(4, cart.getProduct_id());
            pst.setInt(5, cart.getCart_num());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // update
    // 수량, 가격만 변경
    public void updateByProduct(CartDTO cart) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = """
                update carts
                set cart_num = ?, 
                    cart_price = (select product_price from products where product_id = ?) * ?
                where product_id = ?
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cart.getCart_num());
            pst.setInt(2, cart.getProduct_id());
            pst.setInt(3, cart.getCart_num());
            pst.setInt(4, cart.getProduct_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // delete
    public void deleteByProduct(Integer productId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from carts where product_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 구매로 사용
    // 트리거 사용 o
    public void deleteById(String userId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from carts where user_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CartDTO> selectAll(String userId) {
        List<CartDTO> cartList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = """
                select c.*, p.product_name
                from carts c
                join products p on c.product_id = p.product_id
                where c.user_id = ?
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            while(rs.next()) {
                CartDTO cart = makeCart(rs);
                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    private CartDTO makeCart(ResultSet rs) throws SQLException {
        CartDTO cart = CartDTO.builder()
                .cart_id(rs.getInt("cart_id"))
                .user_id(rs.getString("user_id"))
                .product_id(rs.getInt("product_id"))
                .cart_num(rs.getInt("cart_num"))
                .cart_price(rs.getInt("cart_price"))
                .product_name(rs.getString("product_name"))
                .build();

        return cart;
    }

    public CartDTO selectById(int productId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = """
                select c.*, p.product_name
                from carts c
                join products p on c.product_id = p.product_id
                where c.product_id = ?
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, productId);
            rs = pst.executeQuery();

            if(rs.next()) {
                CartDTO cart = makeCart(rs);
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
