package com.home.service.impl;

import com.home.entity.Auth;
import com.home.entity.AuthExample;
import com.home.mapper.AuthMapper;
import com.home.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
