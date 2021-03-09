package com.home.service.impl;

import com.home.entity.Menu;
import com.home.entity.MenuExample;
import com.home.mapper.MenuMapper;
import com.home.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务逻辑层实现类
 *
 * @author 冯根源
 * @date 2021/3/3 23:40
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取素有的的菜单
     *
     * @return
     */
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    /**
     * 保存菜单信息
     *
     * @param menu
     */
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    /**
     * 更新菜心信息
     *
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        //由于pid没有传入，一定要使用有选择的更新，为了保证“pid”不会置空
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 根据ID删除菜单
     *
     * @param id
     */
    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
