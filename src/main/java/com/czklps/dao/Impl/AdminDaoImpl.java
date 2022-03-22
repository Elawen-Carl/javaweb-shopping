package com.czklps.dao.Impl;

import com.czklps.Pojo.Admin;
import com.czklps.Pojo.OrderItem;
import com.czklps.Pojo.User;
import com.czklps.dao.AdminDao;
import com.czklps.exception.*;
import com.czklps.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    /**
     * 插入一个管理员
     * @param admin
     */
    @Override
    public int add(Admin admin) {
        Connection conn = DBUtils.getConn();
        PreparedStatement pstate = null;
        String sql = "insert into `admin` values(null,?,?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setString(1,admin.getName());
            pstate.setString(2,admin.getPhone());
            pstate.setString(3,admin.getEmail());
            pstate.setString(4,admin.getInformation());
            pstate.setObject(5,admin.getPassword());
            pstate.setObject(6,admin.getCreatetime());
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
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int updata(Admin admin) {
        Connection conn = DBUtils.getConn();
        PreparedStatement pstate = null;
        String sql = "update `admin` set `name` = ?, `phone` = ?, `email` = ?, `information` = ?, `password` = ?, `createtime` = ? where id = ?";
        try {
            conn.setAutoCommit(false);

            pstate = conn.prepareStatement(sql);
            pstate.setObject(1,admin.getName());
            pstate.setObject(2,admin.getPhone());
            pstate.setObject(3,admin.getEmail());
            pstate.setObject(4,admin.getInformation());
            pstate.setObject(5,admin.getPassword());
            pstate.setObject(6,admin.getCreatetime());
            pstate.setObject(7,admin.getId());
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
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int delete(Admin admin) {
        Connection conn = DBUtils.getConn();
        Statement stmt = null;
        String sql = "delete from `admin` where id = " + admin.getId();
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            int line = stmt.executeUpdate(sql);
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
            DBUtils.closeState(stmt);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public List<Admin> query() {
        Connection conn = DBUtils.getConn();
        Statement stmt = null;
        String sql = "select * from `admin`";
        ResultSet rs = null;
        try {
            List<Admin> list = new ArrayList<Admin>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setPhone(rs.getString("phone"));
                admin.setEmail(rs.getString("email"));
                admin.setInformation(rs.getString("information"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatetime(rs.getDate("createtime"));
                list.add(admin);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeState(stmt);
            DBUtils.closeConn(conn);
        }
        return null;
    }

    @Override
    public int queryAdmins(List<Admin> admins, String username, String phone, Integer pageNo, Integer pageSize) {
        Connection conn = DBUtils.getConn();
        String sql = "select * from `admin` where 1 = 1";
        Statement state = null;
        ResultSet rs = null;
        ResultSet rsCount = null;
        try {
            rsCount = DBUtils.getRs(sql.replaceFirst("select \\*","select count(*)"));
            state = conn.createStatement();
            if(phone != null && !phone.trim().equals("")){
                sql += " and phone = '" + phone + "'";
            }
            if(username != null && !username.trim().equals("")){
                sql += " and name like '%" + username + "%' ";
            }
            sql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
            rs = state.executeQuery(sql);
            while(rs.next()){
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
                admin.setPhone(rs.getString("phone"));
                admin.setCreatetime(rs.getDate("createtime"));
                admin.setEmail(rs.getString("email"));
                admin.setInformation(rs.getString("information"));
                admins.add(admin);
            }
            rsCount.next();
            return rsCount.getInt("count(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public boolean queryUsernameExist(String name) throws AdminExistException {
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        String sql = "select * from `admin` where `name` = '" + name + "'";
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if(rs.next()) throw new AdminExistException();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return true;
    }

    @Override
    public Admin queryAdminByNameAndPwd(String name, String pwd) throws AdminNotFoundException, PasswordCorrectException {
        Connection conn = DBUtils.getConn();
        String sql = "select * from `admin` where name = ?";
        PreparedStatement pstate = null;
        ResultSet rs = null;
        Admin admin = null;
        try {
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1,name);
            rs = pstate.executeQuery();
            if(!rs.next()){
                throw new AdminNotFoundException();
            } else if(!rs.getString("password").equals(pwd)){
                throw new PasswordCorrectException();
            }
            admin = new Admin();
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setPassword(rs.getString("password"));
            admin.setPhone(rs.getString("phone"));
            admin.setCreatetime(rs.getDate("createtime"));
            admin.setEmail(rs.getString("email"));
            admin.setInformation(rs.getString("information"));
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return admin;
    }

}
