package com.shinhan.service;

import com.shinhan.dao.BusinessDAO;
import com.shinhan.dto.BusinessDTO;

public class BusinessService {
    BusinessDAO businessDAO = new BusinessDAO();

    public BusinessDTO insertBusiness(BusinessDTO business) {
        return businessDAO.insertBusiness(business);
    }

    public void updateById(BusinessDTO business) {
        businessDAO.updateById(business);
    }

    public void deleteById(String businessId) {
        businessDAO.deleteById(businessId);
    }

    public BusinessDTO selectById(String businessId) {
        return businessDAO.selectById(businessId);
    }

    public BusinessDTO login(String businessId, String businessPw) {
        return businessDAO.login(businessId, businessPw);
    }
}