package com.home.controller;

import com.github.pagehelper.PageInfo;
import com.home.entity.Admin;
import com.home.service.AdminService;
import com.home.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            //使用RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带对应参数时使用默认值
            //keyword默认值使用空字符串，和SQL语句配合实现两种情况适配
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            //pageNum默认值使用1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            //pageSize默认值使用5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap

    ) {
        //调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        //将pageInfo存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin-page";
    }

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
        httpSession.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("admin/do/logout.html")
    public String doLogout(HttpSession httpSession) {
        //强制Session失效
        httpSession.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("adminId") Integer adminId,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword") String keyword) {
        //执行删除
        adminService.remove(adminId);
        //页面的跳转：回到分页的页面
        //1 返回admin-page 转发  无法显示分页数据
        //2 转发的forward:/admin/get/page.html地址  一旦刷新页面会重复执行删除，浪费性能
        //3 尝试方案3 重定向/admin/get/page.html地址
        //同时为了保持原本所在的页面和查询关键词在附加pageNum和keyword两个请求参数
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("/admin/save.html")
    public String save(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping("admin/to/edit/page.html")
    public String toEditPage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap
    ) {
        //1根据adminId查询admin对象
        Admin admin = adminService.getAdminById(adminId);
        //2.将Admin对象传入模型
        modelMap.addAttribute("admin", admin);
        return "admin-edit";
    }

    @RequestMapping("admin/update.html")
    public String update(Admin admin, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword) {
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }
}
