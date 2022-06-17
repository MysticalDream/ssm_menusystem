<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String staticPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/menusystem/resources";%>
<html>
<head>
    <title>菜谱详情</title>
    <link rel="stylesheet" href="<%=staticPath%>/external/layui/css/layui.css" media="all">
    <style>
        main {
            width: 980px;
            max-width: calc(100% - 2rem);
            margin: 0 auto;
            /*min-height: calc(100% - var(--height-outer) - 4rem);*/
            /*height: calc(100% - var(--height-outer) - 4rem);*/
            /*max-height: calc(100% - var(--height-outer) - 4rem);*/
            /*margin: 2rem auto;*/
            /*overflow-y: auto;*/
            /*scrollbar-width: none; !* Firefox *!*/
            /*-ms-overflow-style: none; !* IE 10+ *!*/
        }

        body > header > div.category_wrap {
            display: none !important;
        }

        form.form_wrap.layui-form {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin-top: 10px;
            margin-bottom: 20px;
        }

        .layui-disabled, .layui-disabled:hover {
            color: #141e30 !important;
        }

        #test5_wrap {
            display: none;
            margin: 10px;
        }

        #test5_wrap p {
            text-align: center;
        }
    </style>
</head>
<body>
<%@include file="../component/header.jsp" %>
<main>
    <div>
        <form class="form_wrap layui-form" action="javascript:void(0)" id="menuForm" lay-filter="displayFilter">


            <div class="layui-form-item" id="uploadDemoView">
                <img src="" alt="上传成功后渲染" style="max-width: 196px">
            </div>


            <div>
                <div id="test4"></div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label">菜谱名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="menuName" disabled placeholder="请输入菜谱名"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">菜谱介绍</label>
                <div class="layui-input-inline">
            <textarea name="intro" disabled style="width: 400px; height: 150px;resize: none" autocomplete="off"
                      class="layui-textarea" placeholder="请输入菜谱描述"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">菜谱类别</label>
                <div class="layui-input-inline">
                    <select name="cid" disabled>
                        <%--                        <option value="">请选择类别</option>--%>
                        <%--                <option value="美食" selected="">美食</option>--%>
                        <%--                <option value="新闻">新闻</option>--%>
                        <%--                <option value="八卦">八卦</option>--%>
                        <%--                <option value="体育">体育</option>--%>
                        <%--                <option value="音乐">音乐</option>--%>
                    </select>
                </div>
            </div>
            <div>
                <button type="button" class="layui-btn">我要评分</button>
            </div>
        </form>

    </div>
</main>
<%@include file="../component/footer.jsp" %>
<script src="<%=staticPath%>/external/layui/layui.js"></script>
<script src="<%=staticPath%>/js/detail.js" type="module"></script>
</body>

<div id="test5_wrap">
    <div id="test5"></div>
</div>

</html>
