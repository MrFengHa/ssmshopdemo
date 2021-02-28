<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="js/my-role.js"></script>
<script type="text/javascript">
    $(function () {
        //1.为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        // 2.调用执行分页的函数，显示分页效果
        generatePage();

        //给查询按钮绑定单击响应函数
        $("#searchBtn").click(function () {
            //获取关键词数据赋值给对应的全局变量
            window.keyword = $("#keywordInput").val();
            //调用翻页函数刷新页面
            generatePage();
        })

        //点击新增按钮打开模态框
        $("#showAddModalBtn").click(function () {
            $("#addModal").modal("show");
        })


        //给新增模态框中的保存按钮绑定单击响应函数
        $("#saveRoleBtn").click(function () {
            //①获取用户在文本框中输入的角色名称
            //addModal表示找到整个模态框 空格表示在后代元素中继续查找
            //[name=roleName] 表示匹配name属性等于roleName的元素
            let roleName = $.trim($("#addModal [name=roleName]").val());

            //②发送ajax请求
            $.ajax({
                "url": "role/save.json",
                "type": "post",
                "data": {
                    "name": roleName
                },
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if (result = "SUCCESS") {
                        layer.msg("操作成功")
                        generatePage();
                    }
                    if (result == "FILED") {
                        layer.msg("操作失败" + response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + "" + response.statusText)
                }
            });
            //关闭模态框
            $("#addModal").modal("hide");
            //清理模态框
            $("#addModal [name=roleName]").val("");
            //重新加载分页
            window.pageNum = 99999;
            generatePage();
        })


        //给页面上的铅笔绑定响应函数，目的打开模态框
        //使用JQuery对象的on()函数可以解决上面的问题
        //①首先找到所用动态生成的元素附着的静态元素
        //on函数的参数第一个参数是事件类型
        //on函数第二个参数是找到真正要绑定事件的元素选择器
        //on函数第三个参数是事件响应函数
        $("#rolePageBody").on("click", ".pencilBtn", function () {
            $("#editModal").modal("show");
            //获取表格中当前行中的角色名称
            let roleName = $(this).parent().prev().text();

            //获取当前角色的id
            //为了让执行更新的按钮能获取到RoleId的值，把它放到全局变量上
            window.roleId = this.id
            //使用roleName的值设置文本框
            $("#editModal [name=roleName]").val(roleName);
        });

        //给更新模态框中的更新按钮绑定单击响应函数
        $("#updateRoleBtn").click(
            function () {
                //①从文本框中获取新的角色名称
                let roleName = $("#editModal [name=roleName]").val();
                //er发送Ajax请求，执行更新
                $.ajax({
                    "url": "role/update.json",
                    "type": "post",
                    "data": {
                        "id": window.roleId,
                        "name": roleName
                    },
                    "dataType": "json",
                    "success": function (response) {
                        let result = response.result;
                        if (result = "SUCCESS") {
                            layer.msg("操作成功")
                            //从新加载分页数据
                            generatePage();
                        }
                        if (result == "FILED") {
                            layer.msg("操作失败" + response.message);
                        }
                    },
                    "error": function (response) {
                        layer.msg(response.status + "" + response.statusText)
                    }
                })
                //③ 关闭模态框
                $("#editModal").modal("hide");
            })
    })
</script>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="showAddModalBtn" type="button" class="btn btn-primary" style="float:right;"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <form class="form-signin" role="form">

                    <div class="form-group has-success has-feedback">
                        <input type="text" name="roleName" class="form-control" placeholder="请输入角色名称" autofocus>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="updateRoleBtn">更新</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id="addModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <form class="form-signin" role="form">

                    <div class="form-group has-success has-feedback">
                        <input type="text" name="roleName" class="form-control" placeholder="请输入角色名称" autofocus>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveRoleBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>
</html>