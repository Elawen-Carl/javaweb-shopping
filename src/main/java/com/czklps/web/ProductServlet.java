package com.czklps.web;

import com.czklps.Pojo.Cart;
import com.czklps.Pojo.CartItem;
import com.czklps.Pojo.Product;
import com.czklps.service.Impl.ProductServiceImpl;
import com.czklps.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends BaseServlet{
    private ProductService productService = new ProductServiceImpl();

    protected void queryProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.queryProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/pages/index.jsp").forward(req, resp);
    }

    protected void checkProductDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product p = productService.queryProductById(id);
        req.setAttribute("product", p);
        req.getRequestDispatcher("/pages/product/product-detail.jsp").forward(req, resp);
    }

    protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = null;
        if(session.getAttribute("user") == null){
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
        } else {
            if(session.getAttribute("cart") == null){
                cart = new Cart();
                session.setAttribute("cart", cart);
            } else {
                cart = (Cart) session.getAttribute("cart");
            }
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer cartnum = Integer.parseInt(req.getParameter("cartnum"));
            Product product = productService.queryProductById(id);
            CartItem cartItem = new CartItem();
            cartItem.setCount(cartnum);
            cartItem.setPrice(product.getPrice());
            cartItem.setUrl(product.getUrl());
            cartItem.setName(product.getName());
            cartItem.setId(product.getId());
            cart.addCartItem(cartItem);
        }
    }

    protected void checkCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
