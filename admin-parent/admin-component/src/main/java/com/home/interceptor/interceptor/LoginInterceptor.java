package com.home.interceptor.interceptor;

import com.home.entity.Admin;
import com.home.exception.AccessForbidException;
import com.home.util.CrowdConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 *
 * @author 冯根源
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.通过request对象获取Session对象
        HttpSession session = request.getSession();

        //2.尝试从Session域中获取Admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        //3.判断admin是否为空
        if (admin == null) {
            //4.抛出异常
            throw new AccessForbidException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
        //5.如果admin对象不为空，则访问true放行
        return true;
    }
}
