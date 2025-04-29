package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.BusinessDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BusinessDAO {
    // insert
    public int insertBusiness(BusinessDTO business) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = """
                insert into businesses(
                business_id,
                business_pw,
                business_name)
                values(?, ?, ?)
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, business.getBusiness_id());
            pst.setString(2, business.getBusiness_pw());
            pst.setString(3, business.getBusiness_name());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // update
    public int updateById(BusinessDTO business) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        Map<String, Object> dynamicSQL = new HashMap<>();

        if(business.getBusiness_pw() != null) dynamicSQL.put("business_pw", business.getBusiness_pw());
        if(business.getBusiness_name() != null) dynamicSQL.put("business_name", business.getBusiness_name());

        String sql = "update businesses set ";
        String sql2 = " where business_id = ?";
        for(String key : dynamicSQL.keySet()) {
            sql += key + " = ?, ";
        }
        sql = sql.substring(0, sql.length()-1);
        sql += sql2;

        try {
            pst = conn.prepareStatement(sql);
            int i = 1;
            for(String key : dynamicSQL.keySet()) {
                pst.setObject(i++, dynamicSQL.get(key));
            }
            pst.setString(i, business.getBusiness_id());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // delete
    public int deleteById(String businessId) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from businesses where business_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
