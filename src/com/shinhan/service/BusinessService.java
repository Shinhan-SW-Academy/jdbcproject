package com.shinhan.service;

import com.shinhan.dao.BusinessDAO;
import com.shinhan.dto.BusinessDTO;

public class BusinessService {
    BusinessDAO businessDAO = new BusinessDAO();

    public int insertBusiness(BusinessDTO business) {
        return businessDAO.insertBusiness(business);
    }

    public int updateById(BusinessDTO business) {
        return businessDAO.updateById(business);
    }

    public int deleteById(String businessId) {
        return businessDAO.deleteById(businessId);
    }
}
