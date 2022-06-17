
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style>
        html {
            height: 100%;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background: linear-gradient(#141e30, #243b55);
        }

        .register-box {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 400px;
            padding: 40px;
            transform: translate(-50%, -50%);
            background: rgba(0, 0, 0, .5);
            box-sizing: border-box;
            box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
            border-radius: 10px;
        }

        .register-box h2 {
            margin: 0 0 30px;
            padding: 0;
            color: #fff;
            text-align: center;
        }

        .register-box .user-box {
            position: relative;
        }

        .register-box .user-box input {
            width: 100%;
            padding: 10px 0;
            font-size: 16px;
            color: #fff;
            margin-bottom: 30px;
            border: none;
            border-bottom: 1px solid #fff;
            outline: none;
            background: transparent;
        }

        .register-box .user-box label {
            position: absolute;
            top: 0;
            left: 0;
            padding: 10px 0;
            font-size: 16px;
            color: #fff;
            pointer-events: none;
            transition: .5s;
        }

        .register-box .user-box input:focus ~ label,
        .register-box .user-box input:valid ~ label {
            top: -20px;
            left: 0;
            color: #03e9f4;
            font-size: 12px;
        }

        .register-box form a {
            position: relative;
            display: inline-block;
            padding: 10px 20px;
            color: #03e9f4;
            font-size: 16px;
            text-decoration: none;
            text-transform: uppercase;
            overflow: hidden;
            transition: .5s;
            margin-top: 40px;
            letter-spacing: 4px
        }

        .register-box a:hover {
            background: #03e9f4;
            color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 5px #03e9f4,
            0 0 25px #03e9f4,
            0 0 50px #03e9f4,
            0 0 100px #03e9f4;
        }

        .register-box a span {
            position: absolute;
            display: block;
        }

        .register-box a span:nth-child(1) {
            top: 0;
            left: -100%;
            width: 100%;
            height: 2px;
            background: linear-gradient(90deg, transparent, #03e9f4);
            animation: btn-anim1 1s linear infinite;
        }

        @keyframes btn-anim1 {
            0% {
                left: -100%;
            }
            50%, 100% {
                left: 100%;
            }
        }

        .register-box a span:nth-child(2) {
            top: -100%;
            right: 0;
            width: 2px;
            height: 100%;
            background: linear-gradient(180deg, transparent, #03e9f4);
            animation: btn-anim2 1s linear infinite;
            animation-delay: .25s
        }

        @keyframes btn-anim2 {
            0% {
                top: -100%;
            }
            50%, 100% {
                top: 100%;
            }
        }

        .register-box a span:nth-child(3) {
            bottom: 0;
            right: -100%;
            width: 100%;
            height: 2px;
            background: linear-gradient(270deg, transparent, #03e9f4);
            animation: btn-anim3 1s linear infinite;
            animation-delay: .5s
        }

        @keyframes btn-anim3 {
            0% {
                right: -100%;
            }
            50%, 100% {
                right: 100%;
            }
        }

        .register-box a span:nth-child(4) {
            bottom: -100%;
            left: 0;
            width: 2px;
            height: 100%;
            background: linear-gradient(360deg, transparent, #03e9f4);
            animation: btn-anim4 1s linear infinite;
            animation-delay: .75s
        }

        @keyframes btn-anim4 {
            0% {
                bottom: -100%;
            }
            50%, 100% {
                bottom: 100%;
            }
        }

        #login_span {
            display: flex;
            justify-content: center;
            position: absolute;
            right: 40px;
            bottom: 66px;
            color: white;
        }
        #login_span>a{
            position: relative;
            display: inline;
            padding: 0px;
            color: #9afcf0;
            font-size: 16px;
            text-decoration: none;
            text-transform: uppercase;
            transition: .5s;
            margin: 0px;
            letter-spacing: 4px;
        }
        #login_span>a:hover{
            background: transparent;
            color: #fff;
            border-radius: 5px;
            box-shadow:none;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="register-box">
    <h2>注册</h2>
    <form action="/" method="post">
        <div class="user-box">
            <input type="text" name="username" required autocomplete="off">
            <label>用户名</label>
        </div>
        <div class="user-box">
            <input type="password" name="password1" required autocomplete="new-password">
            <label>密码</label>
        </div>
        <div class="user-box">
            <input type="password" name="password2" required autocomplete="new-password">
            <label>确认密码</label>
        </div>
        <a href="javascript:void(0)" onclick="register()">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            注册
        </a>
        <span id="login_span">已有账号?<a href="${pageContext.request.contextPath}/login">去登录</a></span>
        <input type="submit" hidden id="submit-btn">
    </form>

    <script>
        function register() {
            let submitBtn = document.querySelector("#submit-btn");
            submitBtn.click();
        }
    </script>
</div>
</body>
</html>
