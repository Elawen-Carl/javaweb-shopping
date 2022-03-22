<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 波波哥
  Date: 2021/12/25
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="static/images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
            Inway
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                        Dropdown
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled"></a>
                </li>
            </ul>
        </div>
        <ul class="nav justify-content-end">
            <c:if test="${ empty sessionScope.user }">
                <li class="nav-item">
                    <a class="nav-link active" href="pages/user/login.jsp">登录</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="pages/user/regist.jsp">注册</a>
                </li>
            </c:if>
            <c:if test="${ not empty sessionScope.user }">
                <li class="nav-item">
                    <a class="nav-link" href="pages/user/personalcenter.jsp">个人中心</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="pages/cart/cart.jsp">
                    购物车
                    <span class="badge badge-primary">${ sessionScope.cart.totalcount }</span>
                </a>
            </li>
        </ul>
    </div>
</nav>