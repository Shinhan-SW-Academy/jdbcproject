package com.shinhan.common;

import java.sql.*;

public class DBUtil {
    // DB 연결
    public static Connection getConnection() {
        Connection conn = null;

        String url = "jdbc:oracle:thin:@192.168.0.202:1521:XE";
        String userid = "jdbcproject";
        String userpass = "1234";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, userid, userpass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    // DB 해제
    public static void dbDisConnect(Connection conn, Statement st, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dbDisConnect(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(pst != null) pst.close();
            if(conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
