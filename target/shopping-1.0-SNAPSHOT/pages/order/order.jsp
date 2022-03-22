<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="col">
            <p>收货地址</p>
            <div class="row-space row-space-20"></div>
            <p>商品及优惠券</p>
            <div class="row-space row-space-20"></div>
            <c:forEach items="${ requestScope.order.orderitem }" var="orderitem">
            <div class="media align-items-center border-top border-bottom py-4">
                <img src="${ orderitem.url }" class="mr-3 img-fluid" style="width: 80px;" alt="...">
                <div class="media-body">
                    <p class="m-0" style="font-size: 18px; display: inline-block; width: 100%;">
							<span style="line-height: 3;">
								${ orderitem.name }
								<span class="badge badge-danger">hot</span>
							</span>
                        <span class="" style="float: right;line-height: 3;color:#ff3939;">${ orderitem.totalprice }元</span>
                        <label style="float: right; margin-right: 300px; line-height: 3;">${ orderitem.price }元 x ${ orderitem.count }</label>
                    </p>
                </div>
            </div>
            </c:forEach>
            <div class="row-space row-space-20"></div>
            <p class="text-center">订单号: ${ requestScope.order.orderid }</p>
            <div class="row-space row-space-20"></div>
            <ul style="text-align: right;">
                <li>
                    <p>商品件数：
                        <span style="color:#ff3939;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ requestScope.order.totalcount }件</span>
                    </p>
                </li>
                <li>
                    <p>商品总价：
                        <span style="color:#ff3939;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ requestScope.order.totalprice }元</span>
                    </p>
                </li>
                <li>
                    <p>应付总额：
                        <span style="color:#ff3939;"><span class="price">${ requestScope.order.totalprice }</span>元</span>
                    </p>
                </li>
            </ul>
            <div class="row-space row-space-20"></div>
            <div style="text-align: right">
                <a href="pages/cart/cart.jsp" class="btn btn-primary">返回购物车</a>
                <a href="pages/order/paysuccessful.jsp" id="checkout" class="btn btn-primary">去结账</a>
            </div>
        </div>
    </div>
</div>
<div class="row-space row-space-50"></div>
<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
<jsp:include page="/pages/common/script.jsp"></jsp:include>
</html>