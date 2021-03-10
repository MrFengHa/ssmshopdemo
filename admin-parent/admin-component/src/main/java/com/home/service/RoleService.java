package com.home.service;

import com.github.pagehelper.PageInfo;
import com.home.entity.Role;

import java.util.List;

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

    /**
     * 根据id删除角色
     * @param roleIdList
     */
    void removeRole(List<Integer> roleIdList);

    /**
     * 查询已经分配的角色
     * @param adminId
     * @return
     */
    List<Role> getAssignedRole(Integer adminId);

    /**
     * 查询没有被分配的角色
     * @param adminId
     * @return
     */
    List<Role> getUnAssignedRole(Integer adminId);
}
