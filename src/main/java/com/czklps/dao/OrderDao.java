package com.czklps.dao;

import com.czklps.Pojo.Order;
import com.czklps.Pojo.OrderItem;

import java.util.List;

public interface OrderDao {
    public List<Order> queryOrders();
    public List<OrderItem> queryOrderItemsByOrderId(String orderid);
    public int insertOrder(Order order);
    public int insertOrderItem(OrderItem orderItem);
    public Order queryOrderByUserId(Integer id);
    public int queryTotalCount();

}