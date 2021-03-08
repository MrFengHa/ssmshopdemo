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
    //找到附着按钮组的超链接
    let anchorId = treeNode.tId + "_a";

    //执行在超链接后面附加span元素的操作
    $("#" + anchorId).after("<span id='" + btnGroupId + "'>A</span>");
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
