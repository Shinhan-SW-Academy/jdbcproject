package com.shinhan.service;

import com.shinhan.dao.ProductDAO;
import com.shinhan.dto.ProductDTO;

import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAO();

    public void insertProduct(ProductDTO product) {
        productDAO.insertProduct(product);
    }

    public void updateById(ProductDTO product) {
        productDAO.updateById(product);
    }

    public void deleteById(String product_id) {
        productDAO.deleteById(product_id);
    }

    public List<ProductDTO> selectAll() {
        return productDAO.selectAll();
    }

    public List<ProductDTO> selectById(String businessId) {
        return productDAO.selectById(businessId);
    }

    public List<ProductDTO> selectByName(String name) {
        return productDAO.selectByName(name);
    }
}
