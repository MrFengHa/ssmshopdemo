package com.home.service.impl;

import com.home.entity.Auth;
import com.home.entity.AuthExample;
import com.home.mapper.AuthMapper;
import com.home.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2021/3/11 17:10
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    /**
     * 获取角色的权限
     *
     * @return
     */
    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(new AuthExample());
    }

    /**
     * 查询角色分配的权限Id
     *
     * @param roleId 角色Id
     * @return
     */
    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    /**
     * 为角色分配权限
     *
     * @param map
     */
    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        //1.先获取roleId
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        //2.删除就关联关系数据
        authMapper.deleteOldRelationship(roleId);
        //3.获取AuthIdList
        List<Integer> authIdList = map.get("authIdArray");

        //4.判断authIdList是否是有效的
        if (authIdList!=null&&authIdList.size()>0){
            authMapper.insertNewRelationship(roleId,authIdList);
        }
    }

    /**
     * 根据用户ID查询权限名称
     *
     * @param adminId
     * @return
     */
    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
