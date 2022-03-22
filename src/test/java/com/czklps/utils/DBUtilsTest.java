package com.czklps.utils;

import com.czklps.service.Impl.ProductServiceImpl;
import com.czklps.service.ProductService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilsTest {
    ProductService p = new ProductServiceImpl();
    @Test
    void query(){
        p.queryProducts();
    }

    @Test
    void getConn() {
        Connection conn = DBUtils.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from product");
            rs.next();
            System.out.println(rs.getInt("id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}