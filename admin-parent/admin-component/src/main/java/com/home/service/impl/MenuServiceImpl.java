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
}
