package com.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.home.entity.Admin;
import com.home.entity.AdminExample;
import com.home.exception.LoginFailedException;
import com.home.mapper.AdminMapper;
import com.home.service.AdminService;
import com.home.util.CrowdConstant;
import com.home.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    /**
     * 获取用户登录信息
     *
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询Admin对象
        //①创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        //②创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        //③在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        //④调用AdminMapper的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);
        // 2.判断Admin对象是否为null
        if (list == null || list.size() == 0) {
            {
                throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
            }
        }
        if (list.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);
        //3.如果Admin对象为null则抛出异常
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //4.如果Admin对象不为null则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();
        //5.将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPswd);
        //6.将对密码进行比较
        if (!Objects.equals(userPswdDB, userPswdForm)) {
            //7.如果比较结果是不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //8.如果一直则返回Admin对象
        return admin;
    }

    /**
     * 获取所有的用户
     *
     * @param keyword  查询条件
     * @param pageNum  页码
     * @param pageSize 每页的条数
     * @return
     */
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //1.调用PageHelper的静态方法 开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        //2.执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
        //3.封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    /**
     * 根据Id删除用户信息
     *
     * @param adminId
     */
    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }
}
