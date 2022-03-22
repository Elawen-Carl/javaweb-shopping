package com.czklps.dao.Impl;

import com.czklps.Pojo.Order;
import com.czklps.Pojo.OrderItem;
import com.czklps.Pojo.User;
import com.czklps.dao.OrderDao;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserNotFoundException;
import com.czklps.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    /**
     * 查询并返回所有订单
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        Connection conn = DBUtils.getConn();
        String sql = "select * from `order`";
        Statement state = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        Order order = null;
        OrderItem orderItem = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                order = new Order();
                order.setOrderid(rs.getString("id"));
                order.setTotalprice(rs.getBigDecimal("totalprice"));
                order.setTotalcount(rs.getInt("totalcount"));
                order.setCreatetime(rs.getDate("createtime"));
                order.setUserid(rs.getInt("userid"));
                List<OrderItem> orderItems = queryOrderItemsByOrderId(order.getOrderid());
                order.setOrderitem(orderItems);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return orders;
    }

    /**
     * 根据订单号查询此订单号的所有订单项
     * @param orderid
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderid) {
        Connection conn = DBUtils.getConn();
        String sql = "select * from `orderitem` where orderid = '" + orderid + "'";
        Statement state = null;
        ResultSet rs = null;
        List<OrderItem> orderItems = new ArrayList<>();
        try{
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setName(rs.getString("name"));
                orderItem.setUrl(rs.getString("url"));
                orderItem.setPrice(rs.getBigDecimal("price"));
                orderItem.setCount(rs.getInt("count"));
                orderItem.setTotalprice(rs.getBigDecimal("totalprice"));
                orderItem.setOrderid(rs.getString("orderid"));
                orderItems.add(orderItem);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return orderItems;
    }

    /**
     * 插入一个订单
     * @param order
     * @return
     */
    @Override
    public int insertOrder(Order order) {
        Connection conn = DBUtils.getConn();
        PreparedStatement pstate = null;
        String sql = "insert into `order` values(?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, order.getOrderid());
            pstate.setObject(2, order.getTotalprice());
            pstate.setObject(3, order.getTotalcount());
            pstate.setObject(4, order.getCreatetime());
            pstate.setObject(5, order.getUserid());
            for (OrderItem orderItem : order.getOrderitem()) {
                insertOrderItem(orderItem);
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
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    /**
     * 插入一个订单项 orderid 不能为空 成功则返回影响的行数 不成功则返回 0
     * @param orderItem
     * @return
     */
    @Override
    public int insertOrderItem(OrderItem orderItem) {
        Connection conn = DBUtils.getConn();
        PreparedStatement pstate = null;
        String sql = "insert into `orderitem` values(null,?,?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, orderItem.getName());
            pstate.setObject(2, orderItem.getPrice());
            pstate.setObject(3, orderItem.getUrl());
            pstate.setObject(4, orderItem.getCount());
            pstate.setObject(5, orderItem.getTotalprice());
            pstate.setObject(6, orderItem.getOrderid());
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
     * 根据用户 id 查询订单
     * @param id
     * @return
     */
    @Override
    public Order queryOrderByUserId(Integer id) {
        Connection conn = DBUtils.getConn();
        String sql = "select * from `order` where userid = '" + id + "'";
        Statement state = null;
        ResultSet rs = null;
        Order order = null;
        OrderItem orderItem = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                order = new Order();
                order.setOrderid(rs.getString("id"));
                order.setTotalprice(rs.getBigDecimal("totalprice"));
                order.setTotalcount(rs.getInt("totalcount"));
                order.setCreatetime(rs.getDate("createtime"));
                order.setUserid(rs.getInt("userid"));
                List<OrderItem> orderItems = queryOrderItemsByOrderId(order.getOrderid());
                order.setOrderitem(orderItems);
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return order;
    }

    @Override
    public int queryTotalCount() {
        ResultSet rs = DBUtils.getRs("select count(*) from `order`");
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
