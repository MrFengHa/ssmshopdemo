package com.home.service;

import com.home.entity.Admin;

import java.util.List;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/24 14:03
 */
public interface AdminService {
    /**
     * 保存用户
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 获取全部用户信息
     * @return
     */
    List<Admin> getAll();
}
