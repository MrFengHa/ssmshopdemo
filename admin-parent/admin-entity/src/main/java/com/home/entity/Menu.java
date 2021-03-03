package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Menu {
    /**
     * 主键
     */

    private Integer id;
    /**
     * 父节点ID
     */
    private Integer pid;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点附带的URL地址，是将来点击菜单项是要跳转的地址
     */
    private String url;
    /**
     * 节点图标的样式
     */
    private String icon;
    /**
     * 存储子节点的集合，初始化是为了避免空指针异常
     */
    private List<Menu> children = new ArrayList<Menu>();
    /**
     * 控制节点是否默认为打开状态，设置为true默认打开
     */
    private Boolean open = true;


}