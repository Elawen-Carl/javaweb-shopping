package com.czklps.dao.Impl;

import com.czklps.Pojo.Category;
import com.czklps.Pojo.Product;
import com.czklps.Pojo.User;
import com.czklps.dao.UserDao;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;
import com.czklps.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /**
     * 根据 username 和 password 查询用户
     * @param username
     * @param password
     * @return
     * @throws PasswordCorrectException
     * @throws UserNotFoundException
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) throws PasswordCorrectException, UserNotFoundException {
        Connection conn = DBUtils.getConn();
        String sql = "select * from user where username = ?";
        PreparedStatement pstate = null;
        ResultSet rs = null;
        User u = null;
        try {
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1,username);
            rs = pstate.executeQuery();
            if(!rs.next()){
                throw new UserNotFoundException();
            } else if(!rs.getString("password").equals(password)){
                throw new PasswordCorrectException();
            }
            u = new User(rs.getInt("id"),rs.getString("username"),rs.getString("phone"),rs.getString("password"),rs.getDate("createtime"),rs.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return u;
    }

    /**
     * 若存在则返回 true 不存在则 false 并且抛出 userNotFoundException
     * @param username
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public boolean queryUsernameExist(String username) throws UserExistException {
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        String sql = "select * from user where username = '" + username + "'";
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if(rs.next()) throw new UserExistException();
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
    public int queryUsers(List<User> users, String username, String phone, Integer pageNo, Integer pageSize) {
        Connection conn = DBUtils.getConn();
        String sql = "select * from user where 1 = 1";
        Statement state = null;
        ResultSet rs = null;
        ResultSet rsCount = null;
        try {
            rsCount = DBUtils.getRs(sql.replaceFirst("select \\*","select count(*)"));
            state = conn.createStatement();
            if(phone != null && !phone.trim().equals("")){
                sql +=" and phone = '" + phone + "'";;
            }
            if(username != null && !username.trim().equals("")){
                sql += " and username like '%" + username + "%' ";
            }
            sql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
            rs = state.executeQuery(sql);
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setCreateTime(rs.getDate("createtime"));
                user.setEmail(rs.getString("email"));
                users.add(user);
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

    /**
     * 用 insert 插入用户 成功则返回 1 否则返回 0
     * @param user
     * @return
     */
    @Override
    public int insertUserToDatabase(User user) {
        Connection conn = DBUtils.getConn();
        String sql = "insert into user values(null, ?, ?, ?, ?, ?)";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1,user.getUsername());
            pstate.setObject(2,user.getPassword());
            pstate.setObject(3,user.getPhone());
            pstate.setObject(4,user.getCreateTime());
            pstate.setObject(5,user.getEmail());
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

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> queryUsers() {
        Connection conn = DBUtils.getConn();
        String sql = "select * from user";
        Statement state = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setCreateTime(rs.getDate("createtime"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return users;
    }

    /**
     * 根据 id 删除用户 删除成功则返回影响的行数 失败则返回0
     * @param id
     * @return
     */
    @Override
    public int deleteUserById(Integer id) {
        Connection conn = DBUtils.getConn();
        String sql = "delete from `user` where id = ?";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, id);
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

    /**
     * 根据用户 id 修改用户 修改成功则返回影响行数 不成功则返回 0
     * @param u
     * @return
     */
    @Override
    public int updateUserById(User u) {
        Connection conn = DBUtils.getConn();
        String sql = "update `user` set `username` = ?,`password` = ?,`email` = ?,`phone` = ? where id = ?";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, u.getUsername());
            pstate.setObject(2, u.getPassword());
            pstate.setObject(3, u.getEmail());
            pstate.setObject(4, u.getPhone());
            pstate.setObject(5, u.getId());
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
    public int queryTotalCount() {
        ResultSet rs = DBUtils.getRs("select count(*) from user");
        int count = 0;
        try {
            rs.next();
            count = rs.getInt("count(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }


}
