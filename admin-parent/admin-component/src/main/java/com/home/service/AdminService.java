package com.home.service;

import com.github.pagehelper.PageInfo;
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
     *
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 获取全部用户信息
     *
     * @return
     */
    List<Admin> getAll();

    /**
     * 获取用户登录信息
     *
     * @param loginAcct
     * @param userPswd
     * @return
     */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    /**
     * 获取所有的用户
     *
     * @param keyword  查询条件
     * @param pageNum  页码
     * @param pageSize 每页的条数
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据Id删除用户信息
     *
     * @param adminId
     */
    void remove(Integer adminId);

    /**
     * 根据Id查询用户信息
     *
     * @param adminId
     * @return
     */
    Admin getAdminById(Integer adminId);
}
