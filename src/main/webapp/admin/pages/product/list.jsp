<%@ page import="com.czklps.Pojo.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 波波哥
  Date: 2021/12/30
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>product list</title>
    <jsp:include page="../common/basePath.jsp"></jsp:include>
    <link rel="stylesheet" href="static/admin/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="static/admin/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" >
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">关键词查询</label>
                            <div class="layui-input-inline">
                                <input type="text" name="keyword" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">最低价</label>
                            <div class="layui-input-inline">
                                <input type="text" name="lowprice" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">最高价</label>
                            <div class="layui-input-inline">
                                <input type="text" name="highprice" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">类别列表</label>
                            <div class="layui-input-inline">
                                <select name="categoryid" lay-filter="categoryid">

                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit"> 编辑 </a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="static/admin/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="static/admin/js/lay-module/layuimini/miniTab.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;


        $.post("admin/adminServlet?action=queryCategories",function (res) {
            let categories = (JSON.parse(res)).data;
            $("select[name=categoryid]").empty();
            var html = "<option value=''>所有类别</option>"
            for(let i = 0;i<categories.length;i++){
                if(categories[i].isleaf){
                    html += "<option value='"+categories[i].id+"'>"+categories[i].name+"</option>";
                }
            }
            $("select[name=categoryid]").append(html);
            form.render('select');
        });

        table.render({
            elem: '#currentTableId',
            url: 'admin/adminServlet?action=queryProducts',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 100},
                {field: 'id', width: 100, title: 'ID', sort: true},
                {field: 'name', width: 200, title: '商品名称'},
                {field: 'url', width: 250, title: '路径', sort: true},
                {field: 'price', width: 100, title: '价格'},
                {field: 'createtime', width: 150, title: '创建时间'},
                {field: 'descr', title: '描述', width: 400},
                {field: 'category', title: '商品分类名称', width: 100,templet:'<div>{{d.category[0].name}}</div>'},
                {title: '操作', width: 260, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 25, 35, 40, 55],
            limit: 15,
            page: true,
            parseData: function(res){
                return {
                    "code": res.code,
                    "msg": res.msg,
                    "count": res.count,
                    "data": res.data
                }
            },
            templet:function(data){
                if(typeof(data.category[0].name) == "undefined"){
                    return 0;
                } else {
                    return data.category[0].name;
                }
            },
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            layer.alert(result, {
                title: '最终的搜索信息'
            });
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    keyword : data.field.keyword,
                    lowprice : data.field.lowprice,
                    categoryid : data.field.categoryid,
                    highprice : data.field.highprice
                }
            });
            return false;
        });
        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加商品',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    offset: '0px',
                    shadeClose: true,
                    area: ['1000px', '100%'],
                    content: 'admin/adminServlet?action=addProduct',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            console.log(data);
            if (obj.event === 'edit') {
                var index = layer.open({
                    title: '编辑商品',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['70%', '70%'],
                    content: 'admin/adminServlet?action=updateProduct&id=' + data.id + '&name='+data.name+'&url='+data.url+'&price='+data.price+'&descr='+data.descr+'&categoryid='+data.categoryid,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    $.post("admin/adminServlet?action=deleteProduct&id="+obj.data.id,function(res){
                        obj.del();
                        layer.close(index);
                    });
                });
            }
        });

    });
</script>

</body>
</html>
