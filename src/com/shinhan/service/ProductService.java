package com.shinhan.service;

import com.shinhan.dao.ProductDAO;
import com.shinhan.dto.ProductDTO;

import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAO();

    public ProductDTO insertProduct(ProductDTO product) {
        return productDAO.insertProduct(product);
    }

    public ProductDTO updateById(ProductDTO product) {
        return productDAO.updateById(product);
    }

    public void deleteById(Integer productId) {
        productDAO.deleteById(productId);
    }

    public List<ProductDTO> selectAll() {
        return productDAO.selectAll();
    }

    public List<ProductDTO> selectById(String businessId) {
        return productDAO.selectById(businessId);
    }

    public ProductDTO selectById(Integer productId) {
        return productDAO.selectById(productId);
    }

    public List<ProductDTO> selectByName(String name) {
        return productDAO.selectByName(name);
    }
}
