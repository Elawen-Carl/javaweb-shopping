package com.czklps.web;

import com.czklps.Pojo.User;
import com.czklps.exception.PasswordCorrectException;
import com.czklps.exception.UserExistException;
import com.czklps.exception.UserNotFoundException;
import com.czklps.service.Impl.UserServiceImpl;
import com.czklps.service.UserService;

import java.io.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    public void init() {

    }

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        try {
            if(!userService.usernameExist(username)){
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                User u = new User(null, username, password, phone, new Date(), email);
                userService.saveUser(u);
                resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            }
        } catch (UserExistException e) {
            System.out.println("用户名已存在");
            req.setAttribute("msg","username already exist !");
            try {
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            } catch (ServletException servletException) {
                servletException.printStackTrace();
            }
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isLogin = false;
        User u = null;
        try {
            if((u = userService.login(username, password)) != null){
                isLogin = true;
            }
        } catch (UserNotFoundException e) {
            req.setAttribute("msg","user not found ! please input correct!");
        } catch (PasswordCorrectException e) {
            req.setAttribute("msg","password incorrect !");
        }
        if(isLogin){
            HttpSession session = req.getSession();
            session.setAttribute("user",u);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            try {
                System.out.println(req.getAttribute("msg"));
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy() {
    }
}