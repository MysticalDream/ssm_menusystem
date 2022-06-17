<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String staticPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/menusystem/resources";%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ssm_menusytem</title>
    <link rel="stylesheet" href="<%=staticPath%>/css/index.css">
    <link rel="stylesheet" href="<%=staticPath%>/external/layui/css/layui.css">
</head>
<body>
<%@include file="component/header.jsp" %>
<main id="main">

    <div class="item_wrap" id="item_wrap">
        <%--加载项目--%>
    </div>

    <template id="template_item">
        <a class="item" href="javascript:void(0)">
            <div class="item_img">
                <img src="">
            </div>

            <div class="item_title_wrap">
                <span class="item_title">xxxx</span>
            </div>
            <div class="item_corner">
                <span>x.x分</span>
            </div>
        </a>
    </template>

    <template id="template_loading">
        <div class="loading">
            <div class="loading-bar"></div>
            <div class="loading-bar"></div>
            <div class="loading-bar"></div>
            <div class="loading-bar"></div>
        </div>
    </template>

</main>
<%@include file="component/footer.jsp"%>
<script src="<%=staticPath%>/external/layui/layui.js"></script>
<script src="<%=staticPath%>/js/index.js" type="module"></script>
</body>
</html>
