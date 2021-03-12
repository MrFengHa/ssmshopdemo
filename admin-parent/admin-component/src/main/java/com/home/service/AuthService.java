package com.home.service;

import com.home.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * 权限业务逻辑层接口
 *
 * @Author 冯根源
 * @create 2021/3/11 16:55
 */
public interface AuthService {
    /**
     * 获取角色的权限
     * @return
     */
    List<Auth> getAll();

    /**
     * 查询角色分配的权限Id
     * @param roleId
     * @return
     */
    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 为角色分配权限
     * @param map
     */
    void saveRoleAuthRelationship(Map<String, List<Integer>> map);
}
