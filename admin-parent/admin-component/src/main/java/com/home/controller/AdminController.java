package com.home.controller;

import com.home.entity.Admin;
import com.home.service.AdminService;
import com.home.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/24 18:37
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession httpSession
    ) {
        //调用Service方法执行检查
        //这个方法能发挥admin对象说明成功，如果账号密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        //将登录成功返回饿admin对象存入作用域
        httpSession.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("admin/do/logout.html")
    public String doLogout(HttpSession httpSession){
        //强制Session失效
        httpSession.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
}
