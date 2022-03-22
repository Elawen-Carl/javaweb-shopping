<%--
  Created by IntelliJ IDEA.
  User: 波波哥
  Date: 2021/12/25
  Time: 7:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +
            "://" + request.getServerName() +
            ":" + request.getServerPort() + request.getContextPath() +
            "/";
%>
<base href="<%= basePath %>">

<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="static/css/main.css">

