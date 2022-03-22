package com.czklps.service.Impl;

import com.czklps.Pojo.Order;
import com.czklps.Pojo.OrderItem;
import com.czklps.dao.Impl.OrderDaoImpl;
import com.czklps.dao.OrderDao;
import com.czklps.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * 查询所有订单
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        return orderDao.queryOrders();
    }

    /**
     * 根据订单号查询订单项
     * @param orderid
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderid) {
        return orderDao.queryOrderItemsByOrderId(orderid);
    }

    /**
     * 创建一个订单
     * @param order
     * @return 0 表示创建不成功返回 false 其他数表示创建成功返回 true
     */
    @Override
    public boolean createOrder(Order order) {
        return orderDao.insertOrder(order) != 0;
    }

    @Override
    public boolean createOrderItemByOrderId(String orderid) {
        return false;
    }

    @Override
    public int queryTotalCount() {
        return orderDao.queryTotalCount();
    }

}
