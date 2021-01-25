<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>helloWorld</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn1").click(function () {
                $.ajax({
                    "url": "send/array/one.html",//请求目标资源的地址
                    "type": "post",          //请求方式
                    "data": {
                        "array":[5, 8, 12]
                    },        //要发送的请求的参数
                    "dataType": "text",      //如何对待服务器端返回的数据
                    "success": function (response) {//服务器端成功处理请求后调用的回调函数    response是响应体数据

                    },
                    "error": function (response) {//服务器端失败处理请求后调用的回调函数    response是响应体数据

                    }
                })
            })

            $("#btn2").click(function () {
                $.ajax({
                    "url": "send/array/two.html",//请求目标资源的地址
                    "type": "post",          //请求方式
                    "data": {
                        "array":[5, 8, 12]
                    },        //要发送的请求的参数
                    "dataType": "text",      //如何对待服务器端返回的数据
                    "success": function (response) {//服务器端成功处理请求后调用的回调函数    response是响应体数据

                    },
                    "error": function (response) {//服务器端失败处理请求后调用的回调函数    response是响应体数据

                    }
                })
            })
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>
<a href="test/ssm.html">测试ssm整合环境</a>
<br>
<button  id="btn1">Send [5, 8, 12]</button>
<button  id="btn2">Send [5, 8, 12]</button>
</body>
</html>
