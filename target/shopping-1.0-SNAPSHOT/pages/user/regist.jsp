<%--
  Created by IntelliJ IDEA.
  User: 波波哥
  Date: 2021/12/25
  Time: 7:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/pages/common/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/pages/common/nav.jsp"></jsp:include>
<div class="container">
    <div class="row" style="display:flex; justify-content: center">
        <div class="col-md-8">
            <form id="comment-reply-form" class="b-form" method="post" action="userServlet">
                <input type="hidden" name="action" value="regist">
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <p class="h2" style="font-weight: normal; text-transform: uppercase"> regist </p>
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                        <div class="col-sm-12">
                            <input type="text" name="username" class="form-control" id="form-subject" placeholder="User Name">
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                        <div class="col-sm-12">
                            <input type="phone" name="phone" class="form-control" id="form-phone" placeholder="Phone">
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                        <div class="col-sm-6">
                            <input type="password" name="password" class="form-control" id="form-first-name" placeholder="Password">
                        </div>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="form-last-name" placeholder="REPassword">
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                        <div class="col-sm-12">
                            <input type="eamil" name="email" class="form-control" id="" placeholder="Email">
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                        <div class="col-sm-4">
                            <p style="text-transform: uppercase">${ requestScope.msg }</p>
                        </div>
                        <div class="col-sm-12 text-center">
                            <button type="submit" style="text-transform: uppercase;" class="btn btn-primary px-5">submit</button>
                        </div>
                        <div class="col-sm-12" style="text-align:center;">
                            <div class="row-space-20"></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
<jsp:include page="/pages/common/script.jsp"></jsp:include>
</html>
