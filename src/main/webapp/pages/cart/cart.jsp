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
<div class="row-space row-space-30"></div>
<div class="container">
    <div class="row">
        <table class="table-box text-center col">
            <thead class="table-header border-bottom">
            <tr class="">
                <td>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="checkedall">
                        <label class="custom-control-label" for="checkedall">全选</label>
                    </div>
                </td>
                <td></td>
                <td>商品名称</td>
                <td>单价</td>
                <td>数量</td>
                <td>小计</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${ sessionScope.cart.cartitems }" var="cart">
                    <tr>
                        <td>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" name="cartitem" value="${ cart.id }" class="form-check-input">
                            </div>
                        </td>
                        <td class="" style="width: 200px; height: 200px;"><img src="${ cart.url }" class="img-fluid" alt=""></td>
                        <td>${ cart.name }</td>
                        <td>${ cart.price }</td>
                        <td>${ cart.count }</td>
                        <td>${ cart.totalprice }</td>
                        <td><a href="#">删除</a></td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td>
                    <a class="btn btn-primary" href="index.jsp">继续购物</a>
                </td>
                <td>已选择 <span>0</span></td>
                <td></td>
                <td></td>
                <td>共 <span>${ sessionScope.cart.totalcount }</span> 件</td>
                <td>
                    <h4 style="color: #e31717;"><small>合计：</small>￥ <span class="totalprice"></span> 元</h4>
                </td>
                <td>
<%--                    <a href="javascript:void(0)" id="checkout" class="btn btn-primary">去结账</a>--%>
                    <a href="javascript:void(0)" id="checkout" class="btn btn-primary">去结账</a>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<div class="row-space row-space-30"></div>
<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
<jsp:include page="/pages/common/script.jsp"></jsp:include>
<script type="text/javascript">
    $("#checkedall").click(function(){
        if($(this).prop("checked")){
            $("input[name='cartitem']").each(function(index, item){
                this.checked = true;
                console.log($(this).val());;
                $(".totalprice").text()
            })
        } else {
            $("input[name='cartitem']").each(function(index, item){
                this.checked = false;
            })
        }
    })
    $("#checkout").click(function(){
        let data;
        let arr = [];
        $("input[name='cartitem']:checked").each(function(index, item){
            arr.push($(this).val());
        })
        console.log(arr);
        // $.post({
        //     url: "orderServlet?action=createOrder",
        //     data: {},
        //     success: function(res){
        //
        //     }
        // });
    })
</script>
</html>