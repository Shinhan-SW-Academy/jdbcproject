package com.shinhan.service;

import com.shinhan.dao.CartDAO;
import com.shinhan.dto.CartDTO;

public class CartService {
    CartDAO cartDAO = new CartDAO();

    public int insertCart(CartDTO cart) {
        return cartDAO.insertCart(cart);
    }

    public int updateById(CartDTO cart) {
        return cartDAO.updateById(cart);
    }

    public int deleteById(Integer cartId) {
        return cartDAO.deleteById(cartId);
    }
}
