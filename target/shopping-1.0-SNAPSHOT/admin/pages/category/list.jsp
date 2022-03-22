<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>menu</title>
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
    <style>
        .layui-btn:not(.layui-btn-lg ):not(.layui-btn-sm):not(.layui-btn-xs) {
            height: 34px;
            line-height: 34px;
            padding: 0 8px;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <blockquote class="layui-elem-quote">
            Layui的树形表格treeTable，支持异步加载(懒加载)、复选框联动、折叠状态记忆。<br>
            <a href="https://gitee.com/whvse/treetable-lay" target="_blank" class="layui-btn layui-btn-danger">treetable-lay</a>
        </blockquote>
        <div>
            <div class="layui-btn-group">
                <button class="layui-btn" id="btn-expand">全部展开</button>
                <button class="layui-btn layui-btn-normal" id="btn-fold">全部折叠</button>
            </div>
            <table id="munu-table" class="layui-table" lay-filter="munu-table"></table>
        </div>
    </div>
</div>
<!-- 操作列 -->
<script type="text/html" id="auth-state">
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="add">添加</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" data-method="offset" data-type="rb" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="addproduct">在此类别下添加商品</a>
</script>
</script>
<script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['table', 'miniTab', 'treetable'], function () {
        var $ = layui.jquery;
        var table = layui.table,
            miniTab = layui.miniTab;
        var treetable = layui.treetable;

        // 渲染表格
        layer.load(2);
        treetable.render({
            treeColIndex: 1,
            treeSpid: -1,
            treeIdName: 'id',
            treePidName: 'pid',
            elem: '#munu-table',
            url: 'adminServlet?action=queryCategories',
            page: false,
            cols: [[
                {type: 'numbers'},
                {field: 'name', width: 160, title: '类别名称'},
                {field: 'descr', width: 100, title: '类别描述'},
                {field: 'id', align: 'center', title: 'id'},
                {field: 'pid', align: 'center', title: 'pid'},
                {
                    field: 'isleaf', width: 200, align: 'center', templet: function (d) {
                        if (d.isleaf == 0) {
                            return '<span class="layui-badge layui-bg-blue">菜单</span>';
                        } else {
                            return '<span class="layui-badge-rim">分类</span>';
                        }
                    }, title: '类型'
                },
                {
                    width: 300, align: 'center', templet: function (d) {
                        if (d.isleaf == 0) {
                            return '<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="add">添加</a>'+
                                '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>'+
                            '<a class="layui-btn layui-btn-danger layui-btn-xs" data-method="offset" data-type="rb" lay-event="del">删除</a>'
                        } else {
                            return '<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="add">添加</a>'+
                                '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>'+
                                '<a class="layui-btn layui-btn-danger layui-btn-xs" data-method="offset" data-type="rb" lay-event="del">删除</a>'+
                                '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="addproduct">在此类别下添加商品</a>';;
                        }
                    }, title: '操作'
                }
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });

        $('#btn-expand').click(function () {
            treetable.expandAll('#munu-table');
        });

        $('#btn-fold').click(function () {
            treetable.foldAll('#munu-table');
        });

        //监听工具条
        table.on('tool(munu-table)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'del') {
                layer.open({
                    content: '确定要删除 '+data.id+' 吗?'
                    ,btn: ['确定', '取消']
                    ,yes: function(index, layero){
                        $.post("adminServlet?action=deleteCategory",data,function (res) {
                            let data = (JSON.parse(res));
                            if(data.code==1){
                                layer.msg(data.msg);
                            }else{
                                layer.msg(data.msg);
                                parent.window.location.reload();
                            }
                        });
                    }
                    ,closeBtn: function(index, layero){
                        return false
                    },cancel: function(){
                        return false
                    }
                });
            } else if (layEvent === 'edit') {
                layer.open({
                    title: '修改用户',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    offset: '100px',
                    shadeClose: true,
                    area: ['50%', '70%'],
                    content: "edit.jsp",
                    success: function(layero, index){
                        var body=layer.getChildFrame('body',index);//少了这个是不能从父页面向子页面传值的
                        //获取子页面的元素，进行数据渲染
                        body.contents().find('#attributeId').val(data.id);
                        body.contents().find('#categoryName').val(data.name);
                        body.contents().find('#categoryDescr').val(data.descr);
                        let id = '#isValid' + data.isValid;
                    },
                })
            } else if (layEvent === 'add') {
                layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    offset: '100px',
                    shadeClose: true,
                    area: ['50%', '70%'],
                    content: "add.jsp",
                    success: function(layero, index){
                        var body=layer.getChildFrame('body',index);//少了这个是不能从父页面向子页面传值的
                        //获取子页面的元素，进行数据渲染
                        body.contents().find('#attributeId').val(data.id);
                        let id = '#isValid' + data.isValid;
                    },
                })
            } else if (layEvent === 'addproduct'){
                console.log(data.id);
                layer.open({
                    title: '添加商品',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    offset: '100px',
                    shadeClose: true,
                    area: ['50%', '70%'],
                    content: "adminServlet?action=addProduct&categoryid="+data.id
                })
            }
        });
    });
</script>
</body>
</html>