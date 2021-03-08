function myAddDiyDom(treeId, treeNode) {
    console.log("treeId=" + treeId)
    console.log(treeNode)


    //根据id的生成规则拼接出来span标签的id
    let spanId = treeNode.tId + "_ico";
    //根据控制图标的span标签的id找到这个span标签
    $("#" + spanId).removeClass().addClass(treeNode.icon)

}

/**
 * 在鼠标移入节点范围时添加按钮组
 * @param treeId
 * @param treeNode
 */
function myAddHoverDom(treeId, treeNode) {
    //按钮组的标签结构:<span><a><i></i></a><a><i></i></a></span>
    //按钮出现的位置：节点中treeDemo_m_a超链接后面
    //为了在需要移除按钮组的时候能够精确定位到按钮组所在是span，需要给span设置规律的id
    let btnGroupId = treeNode.tId + "_btnGrp";

    //判断一下之前是否添加了按钮组
    if ($("#"+btnGroupId).length>0){
        return;
    }

    //准备各个按钮的HTML标签
    let addBtn ="<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    let removeBtn = "<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    let editBtn ="<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";

    //获取当前节点的级别
    let level = treeNode.level;

    //声明变量存储拼装好的按钮代码
    let btnHTML = "";
    //判断当前节点的级别
    if (level==0){
        btnHTML =btnHTML+ addBtn;
    }
    if (level==1){
        btnHTML = addBtn+" "+editBtn;
        //获取当前的子节点
        let length = treeNode.children.length;

        if (length==0){
            btnHTML = btnHTML+ " "+ removeBtn;
        }
    }
    if(level==2){
        btnHTML = editBtn+" "+removeBtn;
    }

    //找到附着按钮组的超链接
    let anchorId = treeNode.tId + "_a";
    console.log(btnHTML)
    console.log(level)
    //执行在超链接后面附加span元素的操作
    $("#" + anchorId).after("<span id='" + btnGroupId + "'>"+btnHTML+"</span>");
}

/**
 * 在鼠标移除时删除按钮组
 * @param treeId
 * @param treeNode
 */
function removeHoverDom(treeId, treeNode) {
    //找到按钮组的id，
    let btnGroupId = treeNode.tId + "_btnGrp";
    //移除对应的元素

    $("#" + btnGroupId).remove();
}

//生成树形结构的函数
function  generateTree(){
    //1.准备生成树形结构的数据
    $.ajax({
        "url": "menu/get/whole/tree.json",
        "type": "post",
        "dataType": "json",
        "success": function (response) {
            let result = response.result;
            if (result == "SUCCESS") {
                //2.创建JSON对象用于存储对ZTree所做的设置
                let setting = {
                    "view": {
                        "addDiyDom": myAddDiyDom,
                        "addHoverDom": myAddHoverDom,
                        "removeHoverDom": removeHoverDom
                    },
                    "data": {
                        "key": {
                            "url": "maomi"
                        }
                    }
                };
                //3.从响应体中获取用来生成树形结构的JSON数据
                let zNodes = response.data;
                //4.初始化数据结构
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if (result = "FAILED") {
                layer.msg(response.message);
            }
        }
    });
}
