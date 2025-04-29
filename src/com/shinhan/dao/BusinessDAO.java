package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.BusinessDTO;
import com.shinhan.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BusinessDAO {
    // insert
    public BusinessDTO insertBusiness(BusinessDTO business) {
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
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return business;
    }

    // update
    public void updateById(BusinessDTO business) {
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
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete
    public void deleteById(String businessId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from businesses where business_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BusinessDTO login(String businessId, String businessPw) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from businesses where business_id = ? and business_pw = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            pst.setString(2, businessPw);
            rs = pst.executeQuery();

            if(rs.next()) {
                BusinessDTO business = makeBusiness(rs);
                return business;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, rs);
        }

        return null;
    }

    public BusinessDTO selectById(String id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from businesses where business_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if(rs.next()) {
                BusinessDTO business = makeBusiness(rs);
                return business;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, rs);
        }

        return null;
    }

    private BusinessDTO makeBusiness(ResultSet rs) throws SQLException {
        BusinessDTO business = BusinessDTO.builder()
                .business_id(rs.getString("business_id"))
                .business_pw(rs.getString("business_pw"))
                .business_name(rs.getString("business_name"))
                .build();

        return business;
    }
}
