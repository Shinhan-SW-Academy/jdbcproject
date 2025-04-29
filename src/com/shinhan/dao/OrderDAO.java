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
        String sql = "select * from orders where user_id = ?";

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
        String sql = "select * from orders where business_id = ?";

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

    private OrderDTO makeOrder(ResultSet rs) throws SQLException {
        OrderDTO order = OrderDTO.builder()
                .order_id(rs.getInt("order_id"))
                .user_id(rs.getString("user_id"))
                .business_id(rs.getString("business_id"))
                .product_id(rs.getInt("product_id"))
                .order_num(rs.getInt("order_num"))
                .order_price(rs.getInt("order_price"))
                .order_status(OrderStatus.valueOf(rs.getString("order_status")))
                .created_at(rs.getDate("created_at"))
                .build();

        return order;
    }

    public OrderDTO selectById(Integer orderId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from orders where order_id = ?";

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