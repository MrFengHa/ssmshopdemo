package com.home.service.impl;

import com.home.config.SecurityAdmin;
import com.home.entity.Admin;
import com.home.entity.Role;
import com.home.service.AdminService;
import com.home.service.AuthService;
import com.home.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据账号名称查询Admin对象
        Admin admin = adminService.getAdminByLoginAcct(username);
        //2.获取AdminId
        Integer adminId = admin.getId();
        //3.根据Id查询角色信息
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        //4.根据AdminId查询权限信息
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);
        //5.创建集合对象用来存储GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        //6.遍历assignedRoleList存入角色信息
        for (Role role :
                assignedRoleList) {
            //角色要加前缀
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        //7.遍历authNameList存入权限信息
        for (String authName : authNameList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }
        System.out.println(admin.getUserPswd());
        admin.setUserPswd(passwordEncoder.encode(admin.getUserPswd()));
        System.out.println(admin.getUserPswd());
        //9.封装SecurityAdmin对象
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);
        return securityAdmin;
    }
}
