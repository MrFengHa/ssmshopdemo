package com.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.home.entity.Role;
import com.home.mapper.RoleMapper;
import com.home.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务逻辑层实现类
 *
 * @author 冯根源
 * @date 2021/2/17 23:55
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    /**
     * 查询分页根据查询条件
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        //1.开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //2.执行查询
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        //3.封装为PageInfo对象返回

        return new PageInfo<>(roles);
    }
}
