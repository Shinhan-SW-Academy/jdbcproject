package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.OrderDTO;
import com.shinhan.dto.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void updateById(Integer orderId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "update orders set order_status = 'REFUNDED' where order_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // selectByUser
    public List<OrderDTO> selectByUser(String userId) {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = """
                select o.*, p.product_name
                from orders o
                join products p on o.product_id = p.product_id
                where o.user_id = ?
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            while(rs.next()) {
                OrderDTO order = makeOrder(rs);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public List<OrderDTO> selectByBusiness(String businessId) {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = """
                select o.*, p.product_name
                from orders o
                join products p on o.product_id = p.product_id
                where o.business_id = ?
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            rs = pst.executeQuery();

            while(rs.next()) {
                OrderDTO order = makeOrder(rs);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public List<OrderDTO> orderRank(String businessId) {
        List<OrderDTO> rankList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = """
                select o.product_id, sum(o.order_price) as total_price, sum(o.order_num) as total_num, p.product_name
                from orders o
                join products p on o.product_id = p.product_id
                where o.order_status = 'PAID'
                and o.business_id = ?
                group by o.product_id, p.product_name
                order by total_price
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            rs = pst.executeQuery();

            while(rs.next()) {
                OrderDTO rank = rankingOrder(rs);
                rankList.add(rank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rankList;
    }

    static OrderDTO makeOrder(ResultSet rs) throws SQLException {
        OrderDTO order = OrderDTO.builder()
                .order_id(rs.getInt("order_id"))
                .user_id(rs.getString("user_id"))
                .business_id(rs.getString("business_id"))
                .product_id(rs.getInt("product_id"))
                .order_num(rs.getInt("order_num"))
                .order_price(rs.getInt("order_price"))
                .order_status(OrderStatus.valueOf(rs.getString("order_status")))
                .product_name(rs.getString("product_name"))
                .build();

        return order;
    }

    static OrderDTO rankingOrder(ResultSet rs) throws SQLException {
        return OrderDTO.builder()
                .product_id(rs.getInt("product_id"))
                .order_price(rs.getInt("total_price"))
                .order_num(rs.getInt("total_num"))
                .product_name(rs.getString("product_name"))
                .build();
    }

    public OrderDTO selectById(Integer orderId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = """
                select o.*, p.product_name
                from orders o
                join products p on o.product_id = p.product_id
                where o.order_id = ?
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            rs = pst.executeQuery();

            if(rs.next()) {
                OrderDTO order = makeOrder(rs);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}