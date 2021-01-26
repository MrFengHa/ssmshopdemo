package com.home.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断请求类型
 *
 * @author 冯根源
 * @date 2021/1/26 21:04
 */
public class CrowdUtil {
    public static boolean judgeRequestType(HttpServletRequest request) {
        //1获取请求头消息
        String acceptHeader = request.getHeader("Accept");
        String XRequestedHeader = request.getHeader("X-Requested-With");


        return ((acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (XRequestedHeader != null && XRequestedHeader.equals("XMLHttpRequest")));


    }
}
