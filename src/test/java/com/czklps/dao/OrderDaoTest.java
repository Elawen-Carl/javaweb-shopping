package com.czklps.dao;

import com.czklps.Pojo.Order;
import com.czklps.Pojo.OrderItem;
import com.czklps.dao.Impl.OrderDaoImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    void queryOrders() {
        System.out.println(orderDao.queryOrders());
    }

    @Test
    void queryOrderItems(){
        System.out.println(orderDao.queryOrderItemsByOrderId("1641446700997"));
    }

    @Test
    void insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(null);
        orderItem.setName("八享时焦糖味瓜子500g");
        orderItem.setUrl("static/images/product/guazi.jpg");
        orderItem.setCount(2);
        orderItem.setPrice(new BigDecimal(1999));
        orderItem.setOrderid("1641448826965");
        System.out.println(orderDao.insertOrderItem(orderItem));
    }

    @Test
    void insertOrder(){
        Order order = new Order();
        order.setUserid(1);
        order.setCreatetime(new Date());
        order.setOrderid("1641448826965");

        OrderItem orderItem = new OrderItem();

        orderItem.setId(null);
        orderItem.setName("八享时焦糖味瓜子500g");
        orderItem.setUrl("static/images/product/guazi.jpg");
        orderItem.setCount(2);
        orderItem.setPrice(new BigDecimal(1999));
        orderItem.setOrderid("1641448826965");

        order.getOrderitem().add(orderItem);
        System.out.println(orderDao.insertOrder(order));
    }

    @Test
    void queryOrderByUserId(){
        System.out.println(orderDao.queryOrderByUserId(1));
    }

}