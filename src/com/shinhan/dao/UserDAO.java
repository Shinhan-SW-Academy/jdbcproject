package com.shinhan.dao;

import com.shinhan.common.DBUtil;
import com.shinhan.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    // insert
    public UserDTO insertUser(UserDTO user) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = """
                insert into users(
                user_id,
                user_pw,
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
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // update
    public void updateById(UserDTO user) {
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
        sql = sql.substring(0, sql.length()-2);
        sql += sql2;

        try {
            pst = conn.prepareStatement(sql);
            int i = 1;
            for(String key : dynamicSQL.keySet()) {
                pst.setObject(i++, dynamicSQL.get(key));
            }
            pst.setString(i, user.getUser_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete
    public void deleteById(String userId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "delete from users where user_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDTO login(String userId, String userPw) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from users where user_id = ? and user_pw = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            pst.setString(2, userPw);
            rs = pst.executeQuery();

            if(rs.next()) {
                UserDTO user = makeUser(rs);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, rs);
        }

        return null;
    }

    public UserDTO selectById(String userId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from users where user_id = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            if(rs.next()) {
                UserDTO user = makeUser(rs);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisConnect(conn, pst, rs);
        }

        return null;
    }

    private UserDTO makeUser(ResultSet rs) throws SQLException {
        UserDTO user = UserDTO.builder()
                .user_id(rs.getString("user_id"))
                .user_pw(rs.getString("user_pw"))
                .user_name(rs.getString("user_name"))
                .user_phone(rs.getString("user_phone"))
                .user_address(rs.getString("user_address"))
                .build();

        return user;
    }
}
