<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="js/my-menu.js"></script>
<script type="text/javascript">
    $(function () {
        //调用专门封装好的函数初始化树形结构
        generateTree();

        //给添加子节点按钮绑定单击响应函数
        $("#treeDemo").on("click", ".addBtn", function () {
            //将当前节点的ID，作为新节点的pid保存到全局变量中
            window.pid = this.id;
            //打开模态框
            $("#menuAddModal").modal("show");

            return false;
        });

        //给添加子节点的模态框中的保存按钮绑定单机响应函数
        $("#menuSaveBtn").click(function () {
            //收集表单项中用户输入的数据
            let name = $.trim($("#menuAddModal [name=name]").val());
            let url = $.trim($("#menuAddModal [name=url]").val());
            //单选按钮要定位到被选中的哪一个
            let icon = $("#menuAddModal [name=icon]:checked").val();
            //发送ajax请求
            $.ajax({
                "url": "menu/save.json",
                "type": "post",
                "data": {
                    "pid": window.pid,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        //从新加载树形结构
                        generateTree();
                    }

                    if (result == "FAILED") {
                        layer.msg("操作失败" + response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            //关闭模态框
            $("#menuAddModal").modal("hide");
            //清空表单
            $("#menuResetBtn").click();
        });

        //给编辑按钮添加响应函数
        $("#treeDemo").on("click", ".editBtn", function () {
            //将当前节点的ID保存到全局变量中
            window.id = this.id;
            //打开模态框
            $("#menuEditModal").modal("show");

            //获取zTreeObj对象
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            //根据id属性查询节点对象
            //用来搜索节点的属性名
            let key = "id";
            //用来搜索节点的属性值
            let value = window.id;
            let currentNode = zTreeObj.getNodeByParam(key, value);
            //回显表单数据
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);
            //radio回显的本质是把value和currentNode.icon一致的radio选中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);
            return false;
        });

        //给更新模态框中的更新按钮绑定单击响应函数
        $("#menuEditBtn").click(function () {
            //收集表单数据
            let name = $("#menuEditModal [name=name]").val();
            let url = $("#menuEditModal [name=url]").val();
            let icon = $("#menuEditModal [name=icon]:checked").val();
            //发送Ajax请求
            //发送ajax请求
            $.ajax({
                "url": "menu/update.json",
                "type": "post",
                "data": {
                    "id": window.id,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        //从新加载树形结构
                        generateTree();
                    }

                    if (result == "FAILED") {
                        layer.msg("操作失败" + response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            //关闭模态框
            $("#menuEditModal").modal("hide");
        });

        //给删除菜单按钮帮当单机响应函数
        $("#treeDemo").on("click", ".deleteBtn", function () {
            //将当前节点的ID保存到全局变量中
            window.id = this.id;
            //打开模态框
            $("#menuConfirmModal").modal("show");

            //获取zTreeObj对象
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            //根据id属性查询节点对象
            //用来搜索节点的属性名
            let key = "id";
            //用来搜索节点的属性值
            let value = window.id;
            let currentNode = zTreeObj.getNodeByParam(key, value);
            $("#removeNodeSpan").html("【<i class='" + currentNode.icon + "'></i>" + currentNode.name + "】");
            return false;
        });

        //给删除按钮绑定单击响应函数
        $("#confirmBtn").click(function () {
            $.ajax({
                "url": "menu/remove.json",
                "type": "post",
                "data": {
                    "id": window.id
                },
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功");
                        //从新加载树形结构
                        generateTree();
                    }

                    if (result == "FAILED") {
                        layer.msg("操作失败" + response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            //关闭模态框
            $("#menuConfirmModal").modal("hide");
        });
    })
</script>

<body>
<%@include file="/WEB-INF/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree">

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/modal-menu-add.jsp" %>
<%@ include file="/WEB-INF/modal-menu-confirm.jsp" %>
<%@ include file="/WEB-INF/modal-menu-edit.jsp" %>
</body>
</html>
