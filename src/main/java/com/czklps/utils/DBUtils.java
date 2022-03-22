package com.czklps.utils;

import com.mysql.jdbc.Driver;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static String url;
    private static String user;
    private static String password;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    private DBUtils(){
        
    }

    public static Statement getstate(){
        Connection conn = getConn();
        Statement state = null;
        try {
            return state = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }

    public static ResultSet getRs(String sql) {
        Connection conn = getConn();
        Statement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.createStatement();
            return state.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getRs(Connection conn, String sql) {
        Statement state = null;
        ResultSet resultSet = null;
        try {
            state = conn.createStatement();
            return state.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public static void closeRs(ResultSet rs){
        try {
            if(rs != null){
                rs.close();
                rs = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeState(Statement state){
        try {
            if(state != null){
                state.close();
                state = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
                conn = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
