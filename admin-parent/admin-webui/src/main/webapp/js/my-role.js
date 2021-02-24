//生成分页，生成页面效果，调用这个函数都会重新加载页面
function generatePage() {
    //1.获取分页数
    let pageInfo = getPageInfoRemote();
    //2.填充表格
    fillTableBody(pageInfo);
    //3.生成导航栏
    generateNavigator(pageInfo);
}

//远程访问服务器端程序，获取pageInfo数据
function getPageInfoRemote() {
    let ajaxResult = $.ajax({
        "url": "role/get/page/info.json",
        "type": "post",
        "data": {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        "async": false,
        "dataType": "json",
        "success": function () {
            let pageInfo = Response.data;

        },
        "error": function () {

        }
    });

    let statusCode = ajaxResult.status;
    //如果当亲响应状态码不是200，说明发生了错误或者其他意外情况，让当前桉树停止运行
    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + "说明信息" + ajaxResult.statusText);
        return null;
    }

    //如果响应状态码是200，说明处理成功，获取pageInfo
    let resultEntity = ajaxResult.responseJSON;
    //从resultEntity中获取result属性
    let result = resultEntity.result;
    //判断result是否成功
    if (result == "FAILED") {
        layer.msg(resultEntity.message);
    }
    //确认result为成功后获取pageInfo
    let pageInfo = resultEntity.data;

    //返回pageInfo
    return pageInfo;

}


//填充表格
function fillTableBody(pageInfo) {
    //清除tbody中旧的方法
    $("#rolePageBody").empty();
    //清空是为了让没有搜索结果时不显示页码导航条
    $("#Pagination").empty();
    //判断pageInfo是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉没有查询到你搜索的数据！</td></tr>");
        return;
    }
    //使用pageInfo的list属性填充tbody
    for (let i = 0; i < pageInfo.list.length; i++) {
        let role = pageInfo.list[i];
        let roleId = role.id;
        let roleName = role.name;
        let numberTd = "<td>" + (i + 1) + "</td>";
        let checkboxTd = "<td><input type='checkbox'></td>"
        let roleNameTd = "<td>" + roleName + "</td>"
        let checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
        //通过button的id属性把roleId值传递到button按钮的单击响应函数，再单击响应函数中使用this.id
        let pencilBtn = "<button type='button' id='"+roleId+"' class='btn btn-primary btn-xs pencilBtn' ><i class='glyphicon glyphicon-pencil'></i></button>";
        let removeBtn = "<button type='button' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>";
        let buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>"
        let tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>"

        $("#rolePageBody").append(tr);
    }
}

//生成分页页码导航条
function generateNavigator(pageInfo) {
    //获取总记录数
    let totalRecord = pageInfo.total;
    //声明一个JSOS对象存储Pagination要设置属性
    var properties = {
        //边缘页数
        num_edge_entries: 3,
        //主题页数
        num_display_entries: 5,
        //指定用户点击翻页的按钮时跳转页面的回调函数
        callback: pageSelectCallback,
        //每页要显示的数据数量
        items_per_page: pageInfo.pageSize,
        // Pagination内部使用pageIndex来管理页面
        current_page: pageInfo.pageNum - 1,
        //上一页按钮显示的文本
        prev_text: "上一页",
        //下一页按钮显示的文本
        next_text: "下一页",
    }
    //生成页码导航条
    $("#Pagination").pagination(totalRecord, properties)

}

//生成翻页时的回调函数
function pageSelectCallback(pageIndex, jQuery) {
    // 修改 window 对象的 pageNum 属性
    window.pageNum = pageIndex + 1;
    //调用分页函数
    generatePage();
    // 取消页码超链接的默认行为
    return false;
}