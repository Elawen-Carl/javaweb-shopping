<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <jsp:include page="common/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="common/nav.jsp"></jsp:include>
<div class="row-space row-space-50"></div>
<div class="container">
    <div class="row">
        <c:forEach items="${ requestScope.products }" var="product" varStatus="productStatus">
            <div class="col-md-3">
                <div class="card shadow-sm">
                    <div class="card-img-top">
                        <a href="productServlet?action=checkProductDetail&id=${ product.id }">
                            <img src="${ product.url }" class="img-fluid" alt="">
                        </a>
                    </div>
                    <div class="card-body">
                        <a href="productServlet?action=checkProductDetail&id=${ product.id }">
                            <p class="card-price">￥${ product.price } <small>￥300</small></p>
                        </a>
                        <a href="productServlet?action=checkProductDetail&id=${ product.id }">
                            <p class="card-text">${ product.descr }</p>
                        </a>
                    </div>
                </div>
            </div>
            <c:if test="${ productStatus.count % 4 == 0 }">
                <div class="row-space row-space-20"></div>
            </c:if>
        </c:forEach>
    </div>
</div>
<div class="row-space row-space-50"></div>
<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<jsp:include page="common/script.jsp"></jsp:include>
</html>