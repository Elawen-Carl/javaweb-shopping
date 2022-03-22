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
<meta name="keywords" content="layuimini,layui,layui模板,layui后台,后台模板,admin,admin模板,layui mini">
<meta name="description" content="layuimini基于layui的轻量级前端后台管理框架，最简洁、易用的后台框架模板，面向所有层次的前后端程序,只需提供一个接口就直接初始化整个框架，无需复杂操作。">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="admin/images/favicon.ico">
<link rel="stylesheet" href="admin/lib/layui-v2.6.3/css/layui.css" media="all">
<link rel="stylesheet" href="admin/css/layuimini.css?v=2.0.4.2" media="all">
<link rel="stylesheet" href="admin/css/themes/default.css" media="all">
<link rel="stylesheet" href="admin/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
<link rel="stylesheet" href="admin/css/public.css" media="all">
