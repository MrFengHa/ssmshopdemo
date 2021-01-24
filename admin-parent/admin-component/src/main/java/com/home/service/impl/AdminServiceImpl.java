package com.home.service.impl;

import com.home.entity.Admin;
import com.home.entity.AdminExample;
import com.home.mapper.AdminMapper;
import com.home.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/24 14:03
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 保存用户
     *
     * @param admin
     */
    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    /**
     * 获取全部用户信息
     *
     * @return
     */
    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
