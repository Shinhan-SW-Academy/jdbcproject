package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.AccountDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public AccountDTO.UserAccountDTO selectuAcc(String userId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from user_accounts where user_id=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            if(rs.next()) {
                AccountDTO.UserAccountDTO account = makeuAccount(rs);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AccountDTO.BusinessAccountDTO selectbAcc(String businessId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from business_accounts where business_id=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, businessId);
            rs = pst.executeQuery();

            if(rs.next()) {
                AccountDTO.BusinessAccountDTO account = makebAccount(rs);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deposit(String userId, Integer amount) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "update user_accounts set balance = balance + ? where user_id=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, amount);
            pst.setString(2, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private AccountDTO.UserAccountDTO makeuAccount(ResultSet rs) throws SQLException {
        AccountDTO.UserAccountDTO account = AccountDTO.UserAccountDTO.builder()
                .account_id(rs.getInt("account_id"))
                .user_id(rs.getString("user_id"))
                .account_name(rs.getString("account_name"))
                .balance(rs.getInt("balance"))
                .build();

        return account;
    }

    private AccountDTO.BusinessAccountDTO makebAccount(ResultSet rs) throws SQLException {
        AccountDTO.BusinessAccountDTO account = AccountDTO.BusinessAccountDTO.builder()
                .account_id(rs.getInt("account_id"))
                .business_id(rs.getString("business_id"))
                .account_name(rs.getString("account_name"))
                .balance(rs.getInt("balance"))
                .build();

        return account;
    }
}
