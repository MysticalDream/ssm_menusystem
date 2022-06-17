<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String staticPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/menusystem/resources";%>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]> <html class="no-js"> <!--<![endif]-->
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=staticPath%>/css/come.css">
</head>

<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a>
    to improve your experience.</p>
<![endif]-->
<div class="container ${rightActive?"right-panel-active":""}">
    <!-- 注册 -->
    <div class="container__form container--signup">
        <form action="#" class="form" id="form1">
            <h2 class="form__title">注册</h2>
            <div id="regi_msg"></div>
            <input type="text" placeholder="用户名" class="input" autocomplete="off"/>
            <input type="password" placeholder="密码" class="input" autocomplete="new-password"/>
            <input type="password" placeholder="确认密码" class="input" autocomplete="new-password"/>
            <button class="btn" id="reg_btn">注册</button>
        </form>
    </div>

    <!-- 登录 -->
    <div class="container__form container--signin">
        <form action="#" class="form" id="form2">
            <h2 class="form__title">登录</h2>
            <div id="log_msg"></div>
            <input type="text" placeholder="用户名" class="input" autocomplete="off"/>
            <input type="password" placeholder="密码" class="input" autocomplete="new-password"/>
            <button class="btn" id="log_btn">登录</button>
        </form>
    </div>

    <!-- 覆盖 -->
    <div class="container__overlay">
        <div class="overlay">
            <div class="overlay__panel overlay--left">
                <button class="btn" id="signIn">登录</button>
                <button class="btn home" data-home="<%=request.getContextPath()+"/"%>">首页</button>
            </div>
            <div class="overlay__panel overlay--right">
                <button class="btn" id="signUp">注册</button>
                <button class="btn home" data-home="<%=request.getContextPath()+"/"%>">首页</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=staticPath%>/js/come.js" type="module"></script>

</body>

</html>