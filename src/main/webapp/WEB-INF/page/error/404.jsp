<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
    <style>

        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }

        a {
            text-decoration: none;
        }

        a:focus, a:hover {
            text-decoration: underline;
        }

        body {
            text-align: center;
        }


        .page_404 {
            padding: 40px 0;
            background: #fff;
            font-family: SimSun-ExtB, "Microsoft Sans Serif", serif;
            max-width: 900px;
            margin: 0 auto;
        }

        .page_404 img {
            width: 100%;
        }

        .four_zero_four_bg {
            background-image: url(${pageContext.request.contextPath}/menusystem/resources/img/dribbble_1.gif);
            height: 400px;
            background-position: center;
        }


        .four_zero_four_bg h1 {
            font-size: 80px;
        }

        .four_zero_four_bg h3 {
            font-size: 80px;
        }

        .link_404 {
            color: #fff !important;
            padding: 10px 20px;
            background: #39ac31;
            margin: 20px 0;
            display: inline-block;
        }

        .contant_box_404 {
            margin-top: -50px;
        }
    </style>
</head>
<body>
<section class="page_404">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 ">
                <div class="col-sm-10 col-sm-offset-1  text-center">
                    <div class="four_zero_four_bg">
                        <h1 class="text-center ">404</h1>
                    </div>

                    <div class="contant_box_404">
                        <h3 class="h2">
                            看起来你迷路了
                        </h3>

                        <p>您要查找的路径[${requestPath}]不可用！</p>

                        <a href="${pageContext.request.contextPath}/" class="link_404">返回首页</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
