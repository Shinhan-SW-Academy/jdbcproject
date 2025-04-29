package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    // insert
    public int insertUser(UserDTO user) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = """
                insert into users(
                user_id,
                user_pw
                user_name,
                user_phone,
                user_address)
                values(?, ?, ?, ?, ?)
                """;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUser_id());
            pst.setString(2, user.getUser_pw());
            pst.setString(3, user.getUser_name());
            pst.setString(4, user.getUser_phone());
            pst.setString(5, user.getUser_address());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // update
    public int updateById(UserDTO user) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        Map<String, Object> dynamicSQL = new HashMap<>();

        if(user.getUser_pw() != null) dynamicSQL.put("user_pw", user.getUser_pw());
        if(user.getUser_name() != null) dynamicSQL.put("user_name", user.getUser_name());
        if(user.getUser_phone() != null) dynamicSQL.put("user_phone", user.getUser_phone());
        if(user.getUser_address() != null) dynamicSQL.put("user_address", user.getUser_address());

        String sql = "update users set ";
        String sql2 = " where user_id = ?";
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
            pst.setString(i, user.getUser_id());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // delete
    public int deleteById(String userId) {
        int result = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from users where user_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
