package com.home.service;

import com.home.entity.Menu;

import java.util.List;

/**
 * 菜单业务逻辑层接口
 *
 * @author 冯根源
 * @date 2021/3/3 23:39
 */
public interface MenuService {
    /**
     * 获取素有的的菜单
     * @return
     */
    List<Menu> getAll();

    /**
     * 保存菜单信息
     * @param menu
     */
    void saveMenu(Menu menu);
}
