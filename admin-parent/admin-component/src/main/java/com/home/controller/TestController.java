package com.home.controller;

import com.home.entity.Admin;
import com.home.service.AdminService;
import com.home.util.CrowdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/24 18:54
 */
@Controller
@Slf4j
public class TestController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){
        boolean judgeRequest = CrowdUtil.judgeRequestType(request);
        log.info("judgeRequest====================="+judgeRequest);
        List<Admin> adminList= adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        return "target";
    }

    @RequestMapping("/send/array/one.html")
    public String testReceiveArray(@RequestParam("array[]") List<Integer> array, HttpServletRequest request){
        boolean judgeRequest = CrowdUtil.judgeRequestType(request);
        log.info("judgeRequest====================="+judgeRequest);
        for (Integer i:
                array) {
            System.out.println( i);
        }
        return "target";
    }

    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTwo(@RequestBody List<Integer> array){
        for (Integer i:
                array) {
            System.out.println( i);
        }
        return "target";
    }
}
