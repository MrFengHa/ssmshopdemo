package com.home.service.impl;

import com.home.entity.Admin;
import com.home.service.AdminService;
import com.home.service.AuthService;
import com.home.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/3/16 23:24
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据账号名称查询Admin对象
       Admin admin =adminService.getAdminByLoginAcct(username);
        return null;
    }
}
