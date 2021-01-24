package com.home.controller;

import com.home.entity.Admin;
import com.home.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/24 18:54
 */
@Controller
public class TestController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap){

        List<Admin> adminList= adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        return "target";
    }

    @RequestMapping("/send/array.html")
    public String testReceiveArray(@RequestParam("array") List<Integer> array){
        for (Integer i:
                array) {
            System.out.println( i);
        }
        return "target";
    }
}
