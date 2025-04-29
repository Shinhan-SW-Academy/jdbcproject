package com.shinhan.service;

import com.shinhan.dao.OrderDAO;
import com.shinhan.dto.OrderDTO;

import java.util.List;

public class OrderService {
    OrderDAO orderDAO = new OrderDAO();

    public void updateById(Integer orderId) {
        orderDAO.updateById(orderId);
    }

    public OrderDTO selectById(Integer orderId) {
        return orderDAO.selectById(orderId);
    }

    public List<OrderDTO> selectByUser(String userId) {
        return orderDAO.selectByUser(userId);
    }

    public List<OrderDTO> selectByBusiness(String businessId) {
        return orderDAO.selectByBusiness(businessId);
    }
}
