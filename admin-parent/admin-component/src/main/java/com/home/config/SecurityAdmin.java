package com.home.config;

import com.home.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 考虑到User对象仅仅包含账号密码，为了能够获取到原始的Admin对象，专门创建这个类对User进行扩展
 *
 * @author 冯根源
 * @date 2021/3/16 23:15
 */
public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;
    /**
     * 原始的Admin对象，包含Admin对象的全部属性
     */
    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin,
                         //创建角色、权限的信息集合
                         List<GrantedAuthority> authorities) {
        //调用父类构造器
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        //给蓓蕾的this.originalAdmin赋值
        this.originalAdmin = originalAdmin;
    }

    /**
     * 对外提供的获取原始Admin对象的getXXX方法
     * @return
     */
    public Admin getOriginalAdmin() {
        return originalAdmin;
    }

}
