package com.home.service;

import com.github.pagehelper.PageInfo;
import com.home.entity.Role;

/**
 * 角色业务逻辑层接口
 *
 * @author 冯根源
 * @date 2021/2/17 23:54
 */
public interface RoleService {
    /**
     * 查询分页根据查询条件
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 保存角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);
}
