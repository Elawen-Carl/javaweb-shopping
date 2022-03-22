package com.czklps.service;

import com.czklps.Pojo.Order;
import com.czklps.Pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public List<Order> queryOrders();
    public List<OrderItem> queryOrderItemByOrderId(String orderid);
    public boolean createOrder(Order order);
    public boolean createOrderItemByOrderId(String orderid);
    public int queryTotalCount();

}
