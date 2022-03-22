<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.czklps.Pojo.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 波波哥
  Date: 2022/1/2
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
<link rel="stylesheet" href="../../css/public.css" media="all">
<div class="layui-form layuimini-form">
    <input type="hidden" name="id" value="${ requestScope.id }">
    <div class="layui-form-item">
        <label class="layui-form-label required">商品名称</label>
        <div class="layui-input-block">
            <input type="text" value="${ requestScope.name }" name="name" lay-verify="required" lay-reqtext="商品名称不能为空" placeholder="请输入商品名称" value="" class="layui-input">
            <tip>${requestScope.msg}</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">价格</label>
        <div class="layui-input-block">
            <input type="number" value="${ requestScope.price }" name="price" lay-verify="required" lay-reqtext="价格不能为空" placeholder="请输入价格" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">url</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${ requestScope.url }" name="url" lay-verify="required" lay-reqtext="价格不能为空" placeholder="请输入价格" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">描述</label>
        <div class="layui-input-block">
            <textarea name="descr" value="" placeholder="请输入内容" class="layui-textarea">${ requestScope.descr }</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品分类</label>
        <div class="layui-input-block">
            <select name="categoryid" lay-filter="categoryid" lay-verify="required">
                <c:forEach var="category" items="${requestScope.categories}">
                    <c:if test="${ category.leaf == true }">
                        <c:if test="${ requestScope.categoryid == category.id}">
                            <option selected="selected" value="${ category.id }" >${ category.name }</option>
                        </c:if>
                        <c:if test="${ requestScope.categoryid != category.id}">
                            <option value="${ category.id }" >${ category.name }</option>
                        </c:if>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传图标
            </button>
            <br><br>
            <div style="width:200px;height:200px;border:1px solid #0099CC;border-radius: 1px;padding: 3px;">
                <img style="max-width: 200px;max-height:200px;" id="preview">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="saveBtn" class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['form','upload','layer'], function () {
        var form = layui.form,
            upload = layui.upload,
            miniTab = layui.miniTab,
            $ = layui.jquery;
        //添加判断
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            $.post("adminServlet?action=updateProductOk",data.field,function (res) {
                console.log(data.field);
                parent.window.location.reload();
            });
        });
        // 监听提交
        upload.render({
            elem: '#upload',
            url: 'adminServlet?action=updateProductOk',
            auto: false,//选择文件后不自动上传
            bindAction: '#saveBtn',
            //上传前的回调
            before: function () {
                this.data = {
                    id: $('input[name="id"]').val(),
                    url: $('input[name="url"]').val(),
                    name: $('input[name="name"]').val(),
                    price: $('input[name="price"]').val(),
                    descr: $('textarea[name="descr"]').val(),
                    categoryid: $('select[name="categoryid"]').val(),
                }
            },
            //选择文件后的回调
            choose: function (obj) {
                obj.preview(function (index, file, result) {
                    $('#preview').attr('src', result);
                })
                console.log(obj);
            },
            //操作成功的回调
            done: function (res, index, upload) {
                var code = res.code !== 0 ? 1 : 2;
                layer.alert(res.msg, {icon: code}, function () {
                    parent.window.location.reload();
                })

            },
            //上传错误回调
            error: function (index, upload) {
                layer.alert('上传失败！' + index);
            }
        });
    });
</script>