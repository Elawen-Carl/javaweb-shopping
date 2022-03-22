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
<div class="container">
    <div class="row justify-content-between">
        <div class="row-space row-space-30"></div>
        <div class="col-md-4">
            <img src="${ requestScope.product.url }" class="img-fluid border" alt="">
            <div class="row-space row-space-20"></div>
            <a href="#">关注</a> <a href="#">分享</a>
        </div>
        <div class="col-md-8" style="border-left: 1px solid #eeeeee;">
            <h5 style="font-weight: 500;">${ requestScope.product.name }  ${ requestScope.product.descr }</h5>
            <p> <span style="color: #797979;">秒 杀 价</span> <span class="price">￥${ requestScope.product.price }</span></p>
            <div class="row">
                <div class="col-2">
                    <div class="cart-group">
                        <input type="text" readonly="readonly" value="1" name="cart-num" class="cart-group-input form-control">
                        <div class="cart-group-add">+</div>
                        <div class="cart-group-div">-</div>
                    </div>
                </div>
                <div class="col">
                    <a href="javascript:void(0)" class="addToCart">
                        <div class="add-cart">加入购物车</div>
                    </a>
                </div>
            </div>
            <div class="row-space row-space-20"></div>
            <div class="worry">
                温馨提示
                ·支持7天无理由退货
            </div>
            <div class="row-space row-space-30"></div>
        </div>

    </div>
    <div class="row">
        <div class="col text-right">
            <a href="index.jsp" class="btn btn-primary">返回首页</a>
        </div>
    </div>
</div>
<div class="row-space row-space-30"></div>
<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
<jsp:include page="/pages/common/script.jsp"></jsp:include>
<script type="text/javascript">
    $('.addToCart').click(function (){
        $.post({
            url:"productServlet?action=addCart&id=${requestScope.product.id}&cartnum="+$('input[name="cart-num"]').val(),
            success:function(res){
                if(${ sessionScope.user == null }) {
                    confirm("请先进行登录操作，才能将物品添加至购物车。click the confirm redirect login page!");
                    window.location.href = "pages/user/login.jsp";
                    // let page = window.open("about:blank","_self");
                    // page.document.write(res);
                } else {
                    confirm("添加成功");
                    window.location.reload();
                }
            }
        })
    });
    $('.cart-group-add').click(function (){
        $('input[name="cart-num"]').val(parseInt($('input[name="cart-num"]').val()) + 1)
        console.log(parseInt($('input[name="cart-num"]').val()));
    })
    $('.cart-group-div').click(function (){
        if(parseInt($('input[name="cart-num"]').val()) == 0) return;
        $('input[name="cart-num"]').val(parseInt($('input[name="cart-num"]').val()) - 1)
    })
</script>
</html>