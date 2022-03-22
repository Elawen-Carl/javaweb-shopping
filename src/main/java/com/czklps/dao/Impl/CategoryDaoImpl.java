package com.czklps.dao.Impl;

import com.czklps.Pojo.Category;
import com.czklps.Pojo.User;
import com.czklps.dao.CategoryDao;
import com.czklps.utils.DBUtils;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public int addCategory(Category c) {
        Connection conn = DBUtils.getConn();
        PreparedStatement pstate = null;
        ResultSet rs = null;
        Statement state = null;
        String sql = "insert into `category` values(null,?,?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            rs=DBUtils.getRs("select * from `category` where id = " + c.getPid());
            rs.next();
            int isleaf = rs.getInt("isleaf");
            pstate = conn.prepareStatement(sql);
            pstate.setInt(1, c.getPid());
            pstate.setString(2, c.getName());
            pstate.setInt(3,0);
            pstate.setInt(4,rs.getInt("grade") + 1);
            pstate.setObject(5, new Date());
            pstate.setString(6,c.getDescr());
            if(isleaf == 0){
                state = DBUtils.getstate();
                state.executeUpdate("update `category` set `isleaf` = 1 where id = " + c.getPid());
            }
            int line = pstate.executeUpdate();
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeRs(rs);
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int updateCategoryById(Category c) {
        Connection conn = DBUtils.getConn();
        PreparedStatement pstate = null;
        ResultSet rs = null;
        Statement state = null;
        String sql = "update `category` set name = ?, descr = ? where id = ?";
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, c.getName());
            pstate.setString(2, c.getDescr());
            pstate.setInt(3,c.getId());
            int line = pstate.executeUpdate();
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int deleteCategoryById(Integer id, Integer pid, boolean isLeaf){
        Connection conn = DBUtils.getConn();
        try {
            conn.setAutoCommit(false);
            int line = deleteCategory(conn, id, pid, isLeaf);
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    public int deleteCategory(Connection conn, Integer id, Integer pid, boolean isLeaf) throws SQLException {
        //删除子
        if(!isLeaf){
            String sql = "select * from `category` where pid = " + id;
            Statement stmt = null;
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            deleteCategory(conn, rs.getInt("id"),rs.getInt("pid"),rs.getInt("isleaf")==0);
        }
        Statement stmtDelete = conn.createStatement();
        return stmtDelete.executeUpdate("delete from `category` where id = " + id);
    }

    @Override
    public Category queryCategoryNameById(Integer id) {
        Connection conn = DBUtils.getConn();
        String sql = "select * from category where id = " + id;
        Statement state = null;
        ResultSet rs = null;
        Category c = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            rs.next();
            c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setPid(rs.getInt("pid"));
            c.setGrade(rs.getInt("grade"));
            c.setCreatetime(rs.getDate("createtime"));
            c.setDescr(rs.getString("descr"));
            c.setLeaf(rs.getInt("isleaf") == 0 ? true : false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return c;
    }

    @Override
    public int queryTotalCount() {
        ResultSet rs = DBUtils.getRs("select count(*) from category");
        int count = 0;
        try {
            rs.next();
            count = rs.getInt("count(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Category> queryTotalCategory() {
        List<Category> categories = new ArrayList<>();
        Connection conn = DBUtils.getConn();
        try{
            getCategories(conn, categories, -1);
        } finally {
            DBUtils.closeConn(conn);
        }
        return categories;
    }

    private void getCategories(Connection conn, List<Category> categories, int id){
        String sql = "select * from category where pid = " + id;
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setPid(rs.getInt("pid"));
                c.setGrade(rs.getInt("grade"));
                c.setCreatetime(rs.getDate("createtime"));
                c.setDescr(rs.getString("descr"));
                c.setLeaf(rs.getInt("isleaf") == 0 ? true : false);
                categories.add(c);
                if(!c.isLeaf()){
                    getCategories(conn, categories, c.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
