package com.shinhan.service;

import com.shinhan.dao.CartDAO;
import com.shinhan.dto.CartDTO;
import com.shinhan.dto.ProductDTO;

import java.util.List;

public class CartService {
    CartDAO cartDAO = new CartDAO();

    public void insertCart(CartDTO cart) {
        cartDAO.insertCart(cart);
    }

    public void deleteById(String userId) {
        cartDAO.deleteById(userId);
    }

    public void updateByProduct(CartDTO cart) {
        cartDAO.updateByProduct(cart);
    }

    public List<CartDTO> selectAll(String userId) {
        return cartDAO.selectAll(userId);
    }

    public CartDTO selectById(int productId) {
        return cartDAO.selectById(productId);
    }

    public void deleteByProduct(Integer productId) {
        cartDAO.deleteByProduct(productId);
    }
}
