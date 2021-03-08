<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="js/my-menu.js"></script>
<script type="text/javascript">
    $(function () {

        //1.准备生成树形结构的数据
        $.ajax({
            "url":"menu/get/whole/tree.json",
            "type":"post",
            "dataType":"json",
            "success":function (response) {
                let result = response.result;
                if(result=="SUCCESS"){
                    //2.创建JSON对象用于存储对ZTree所做的设置
                    let setting = {
                        "view" :{
                            "addDiyDom":myAddDiyDom,
                            "addHoverDom":myAddHoverDom,
                            "removeHoverDom":removeHoverDom
                        },
                        "data":{
                            "key":{
                                "url":"maomi"
                            }
                        }
                    };
                    //3.从响应体中获取用来生成树形结构的JSON数据
                    let zNodes = response.data;
                    //4.初始化数据结构
                    $.fn.zTree.init($("#treeDemo"),setting,zNodes);
                }
                if(result="FAILED"){
                    layer.msg(response.message);
                }
            }
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

</body>
</html>
