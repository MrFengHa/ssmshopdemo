function send(){
    window.alert("11111111")
}
$("#btn1").click(function () {
    alert("111111")
    $.ajax({
        "url": "send/array.html",//请求目标资源的地址
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