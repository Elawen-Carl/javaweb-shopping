package com.czklps.web;

import com.czklps.Pojo.*;
import com.czklps.service.Impl.OrderServiceImpl;
import com.czklps.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null) return;
        Cart cart = (Cart)session.getAttribute("cart");
        User user= (User)session.getAttribute("user");
        Order order = new Order(user.getId());
        for (CartItem cartitem : cart.getCartitems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartitem.getCount());
            orderItem.setPrice(cartitem.getPrice());
            orderItem.setUrl(cartitem.getUrl());
            orderItem.setName(cartitem.getName());
            orderItem.setOrderid(order.getOrderid());
            order.addOrderItem(orderItem);
        }
        order.setCreatetime(new Date());
        if(orderService.createOrder(order)){
            req.setAttribute("order",order);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
        }
    }

}
