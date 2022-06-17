<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String staticPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/menusystem/resources";%>
<html>
<head>
    <title>管理页面</title>
    <link rel="stylesheet" href="<%=staticPath%>/external/layui/css/layui.css" media="all">
    <style>

        ::-webkit-scrollbar {
            width: 10px;
            height: 10px;
        }

        ::-webkit-scrollbar-track {
            width: 6px;
            background: transparent;
            -webkit-border-radius: 2em;
            -moz-border-radius: 2em;
            border-radius: 2em;
        }

        ::-webkit-scrollbar-thumb {
            background-color: rgba(144, 147, 153, .5);
            background-clip: padding-box;
            min-height: 28px;
            -webkit-border-radius: 2em;
            -moz-border-radius: 2em;
            border-radius: 2em;
            transition: background-color .3s;
            cursor: pointer;
        }

        ::-webkit-scrollbar-thumb:hover {
            background-color: rgba(144, 147, 153, .3);
        }

        /*main {*/
        /*    position: relative;*/
        /*    width: 980px;*/
        /*    max-width: calc(100% - 2rem);*/
        /*    min-height: calc(100% - 111px - 4rem);*/
        /*    height: calc(100% - 111px - 4rem);*/
        /*    max-height: calc(100% - 111px - 4rem);*/
        /*    margin: 2rem 3rem 0 auto;*/
        /*}*/

        .custom_wrap {
            display: flex;
            justify-content: space-between;
        }

        .layui-table-cell .layui-form-checkbox[lay-skin=primary] {
            top: 50%;
            transform: translateY(-50%);
        }

        .form_wrap {
            display: none;
        }

        .custom_scroll {
            position: absolute;
            top: 0;
            left: 0;
        }

        body > main > div {
            background-color: #f2f2f2;
        }

        .layui-layout-admin .layui-side, .layui-layout-admin .layui-body {
            top: 111px;
        }

        .layui-layout-admin .layui-body .custom_body {
            padding: 10px 10px 0px 10px;
            min-height: 100%;
        }

        ul.layui-nav.layui-nav-tree {
            position: relative;
            padding: 0px;
            background-color: #f2f2f2;
            color: #393D49;
            border-radius: 2px;
            font-size: 14px;
        }

        body > main > div > div > ul > li a, body > main > div > div > ul > li a:hover {
            color: #393D49 !important;
        }

        body > main > div > div > ul > li.layui-nav-item.layui-nav-itemed > a {
            color: #393D49 !important;
        }

        ul.layui-nav.layui-nav-tree a {
            line-height: 40px !important;
            text-indent: 3rem;
        }

        body > main > div > div > ul > li {
            display: block !important;
        }

        body > main > div > div > ul > li i {
            position: absolute !important;
        }

        #test10 {
            position: relative;
            left: 50%;
            transform: translateX(-50%);
            margin: 1rem auto;
        }


    </style>
</head>
<body>
<%@include file="../../component/header.jsp" %>
<%--头部样式重置--%>
<style>
    header {
        height: 110px;
    }
    div.category_wrap {
        display: none;
    }

</style>


<main class="layui-layout layui-layout-admin">

    <div class="layui-side">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="nav">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:void(0)">菜谱管理</a>
                    <i class="layui-icon layui-icon-app" style="font-size: 28px;top: 3px;left: 32px;"></i>
                </li>
                <li class="layui-nav-item">

                    <a href="javascript:void(0)">个人信息</a>
                    <i class="layui-icon layui-icon-username" style="font-size: 28px;top: 3px;left: 32px;"></i>
                </li>
                <span class="layui-nav-bar"></span>
            </ul>
        </div>
    </div>

    <section class="layui-body">

        <div class="custom_body" id="tab1">
            <div class="custom_wrap">
                <div class="demoTable">
                    搜索菜谱：
                    <div class="layui-inline">
                        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
                    </div>
                    <button class="layui-btn" data-type="reload">搜索</button>
                </div>
                <button data-type="setTop" class="layui-btn">上传菜谱</button>
            </div>
            <table class="layui-hide" id="table_container" lay-filter="test"></table>
        </div>

        <div class="custom_body layui-hide" id="tab2">
            <h1>个人信息</h1>
        </div>

    </section>
</main>

<%@include file="../../component/footer.jsp" %>

<script src="<%=staticPath%>/external/layui/layui.js"></script>
<script src="<%=staticPath%>/js/admin/manager.js" type="module"></script>

</body>

<%--<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">--%>
<%--    --%>
<%--    <script type="text/html" template="">--%>
<%--        <input type="hidden" name="id" value="{{ d.params.id || '' }}">--%>
<%--    </script> <input type="hidden" name="id" value="001">--%>
<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label"></label>--%>
<%--        <div class="layui-input-inline">--%>
<%--            <input type="button" lay-submit="" lay-filter="layuiadmin-app-form-submit" value="确认" class="layui-btn">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<form class="form_wrap layui-form" action="javascript:void(0)" id="menuForm" lay-filter="formFilter">

    <div class="layui-upload-drag" id="test10">
        <div class="click_wrap" id="click_wrap">
            <i class="layui-icon layui-icon-upload"></i>
            <p>封面上传，点击或将图片拖拽到此处</p>
        </div>
        <div class="layui-hide" id="uploadDemoView">
            <hr>
            <img src="" alt="上传成功后渲染" style="max-width: 196px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">菜谱名称</label>
        <div class="layui-input-inline">
            <input type="text" name="menuName" lay-verify="required" placeholder="请输入菜谱名"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">菜谱介绍</label>
        <div class="layui-input-inline">
            <textarea name="intro" lay-verify="required" style="width: 400px; height: 150px;" autocomplete="off"
                      class="layui-textarea" placeholder="请输入菜谱描述"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">菜谱类别</label>
        <div class="layui-input-inline">
            <select name="cid" lay-verify="required">
                <%--                <option value="">请选择类别</option>--%>
                <%--                <option value="美食" selected="">美食</option>--%>
                <%--                <option value="新闻">新闻</option>--%>
                <%--                <option value="八卦">八卦</option>--%>
                <%--                <option value="体育">体育</option>--%>
                <%--                <option value="音乐">音乐</option>--%>
            </select>
        </div>
    </div>
    <button lay-submit lay-filter="go" class="layui-hide" id="submitBtn"></button>
</form>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="deleteSelected">删除选中菜谱</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</html>
