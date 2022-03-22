package com.czklps.service;

import com.czklps.Pojo.Order;
import com.czklps.Pojo.OrderItem;
import com.czklps.service.Impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    void queryOrders() {
        System.out.println(orderService.queryOrders());
    }

    @Test
    void queryOrderItemByOrderId() {
        System.out.println(orderService.queryOrderItemByOrderId("1641446700997"));
    }
    @Test
    void createOrder(){
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
        orderService.createOrder(order);
    }

}