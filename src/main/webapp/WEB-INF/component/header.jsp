<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% String staticPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/menusystem/resources";%>--%>
<html>
<head>
    <title>头部模板</title>
    <style>
        :root {
            --height-outer: 140px;
            --height-inner1: 60px;
            --height-inner2: 30px;
        }

        header {
            display: flex;
            flex-direction: column;
            align-items: center;
            position: sticky;
            height: var(--height-outer);
            top: calc(var(--height-inner1) + var(--height-inner2) - var(--height-outer));
            border-bottom: 1px solid #ccc;
            background-color: #ffffff;
            z-index: 999;
            justify-content: space-between;
        }

        nav {
            display: flex;
            line-height: var(--height-inner1);
            width: 980px;
            max-width: calc(100% - 2rem);
            margin: 0 auto;
            justify-content: space-between;
            position: sticky;
            top: 0;
        }

        nav a {
            color: #000000;
            display: inline-block;
            padding: 10px 15px;
            line-height: 1;
            text-decoration: none;
            align-self: center;
        }

        .spacing {
            flex: 1;
        }

        nav > span {
            font-size: 20px;
            font-weight: bolder;
            margin-left: 10px;
        }

        body > header > nav > span:nth-child(1) {
            cursor: pointer;
        }

        .search-form {
            display: flex;
            align-items: center;
        }

        input#search-box {
            width: 100%;
            border: 3px solid #d2cfcf;
            border-right: none;
            padding: 5px;
            height: 30px;
            border-radius: 5px 0 0 5px;
            outline: none;
        }

        .searchButton {
            position: relative;
            width: 40px;
            height: 30px;
            border: 1px solid #aab8ba;
            background: #afb3b3;
            text-align: center;
            color: #fff;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            font-size: 20px;
        }

        .cycle {
            display: inline-block;
            height: 18px;
            width: 18px;
            border: 2px solid white;
            border-radius: 50%;
        }

        .searchButton::after {
            content: "";
            position: absolute;
            width: 2px;
            height: 10px;
            right: 4px;
            top: 18px;
            background: #FFFFFF;
            border-radius: 3px;
            transform: rotate(-45deg);
        }

        div.category_wrap {
            display: flex;
            justify-content: space-between;
            width: 980px;
            max-width: calc(100% - 2rem);
            margin: 0 auto;
            line-height: var(--height-inner2);
            z-index: -1;
        }

        div.category {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
        }

        div.category a {
            margin: 0 10px;
        }

        div.category_wrap .category a {
            position: relative;
            color: #141e30;
        }

        a.current {
            color: #fd1b1b !important;
        }

        a.current::after {
            content: '';
            position: absolute;
            left: 0;
            bottom: 0px;
            width: 100%;
            height: 2px;
            background: #fd1b1b;
        }

        .layui-nav .layui-nav-item {
            display: flex;
            align-items: center;
        }

        .layui-nav .layui-nav-more {
            position: static;
        }

        .layui-nav {
            position: sticky;
            padding: 0;
            background-color: white;
            color: #000000;
            border-radius: 2px;
            font-size: inherit;
        }

        .layui-nav .layui-nav-child a {
            line-height: unset;
        }


    </style>
</head>
<body>
<header class="nav_wrap">
    <nav class="layui-nav" lay-bar="disabled">
        <span>菜谱系统</span>
        <div class="spacing"></div>
        <form class="search-form">
            <input type="text" autocomplete="off" id="search-box" placeholder="搜索一下">
            <button class="searchButton">
                <span class="cycle"></span>
            </button>
        </form>

        <c:choose>

            <c:when test="${loginUser==null}">
                <a href="${pageContext.request.contextPath}/login">登录</a>
                <a href="${pageContext.request.contextPath}/register">注册</a>
            </c:when>

            <c:when test="${loginUser.role=='admin'}">
                <li class="layui-nav-item" lay-unselect="">
                    <a href="javascript:;" style="color: #141e30">
                        <img src="${loginUser.avatar}" class="layui-nav-img">
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="<%=request.getContextPath()%>/management?tab=1">管理菜单</a></dd>
                        <dd><a href="<%=request.getContextPath()%>/management?tab=2">个人信息</a></dd>
                        <hr>
                        <dd style="text-align: center;" id="logout"><a href="javascript:void(0)">退出登录</a></dd>
                    </dl>
                </li>
            </c:when>
            <c:otherwise>
                <li class="layui-nav-item" lay-unselect="">
                    <a href="javascript:;" style="color: #141e30">
                        <img src="" class="layui-nav-img">
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">个人信息</a></dd>
                        <hr>
                        <dd style="text-align: center;" id="logout"><a href="javascript:void(0)">退出登录</a></dd>
                    </dl>
                </li>
            </c:otherwise>
        </c:choose>

    </nav>

    <div class="category_wrap">
        <div class="category">
            <a href="javascript:void(0)" class="current" data-flag='{"type":0}'>全部</a>
            <a href="javascript:void(0)" data-flag='{"type":1}'>好评</a>
            <a href="javascript:void(0)" data-flag='{"type":2}'>最新</a>
        </div>
    </div>
</header>

<script type="module" src="<%=staticPath%>/js/component/header.js"></script>
</body>
</html>
