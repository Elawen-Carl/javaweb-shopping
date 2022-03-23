<%--
  Created by IntelliJ IDEA.
  User: 波波哥
  Date: 2021/12/30
  Time: 14:59
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

