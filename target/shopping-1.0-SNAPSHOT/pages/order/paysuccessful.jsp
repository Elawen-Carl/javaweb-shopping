<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <jsp:include page="/pages/common/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/pages/common/nav.jsp"></jsp:include>
<div class="row-space row-space-50"></div>
<div class="container">
    <div class="row">
        <div class="col text-center">
            <img src="static/images/paysuccessful.png" class="img-fluid" alt="">
            <div class="row-space row-space-20"></div>
            <p class="h4">支付成功</p>
            <div class="row-space row-space-20"></div>
            <a href="pages/cart/cart.jsp" class="btn btn-primary">返回购物车</a>
            <a href="index.jsp" id="checkout" class="btn btn-primary">返回首页</a>
        </div>
    </div>
</div>
<div class="row-space row-space-50"></div>
<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
<jsp:include page="/pages/common/script.jsp"></jsp:include>
</html>