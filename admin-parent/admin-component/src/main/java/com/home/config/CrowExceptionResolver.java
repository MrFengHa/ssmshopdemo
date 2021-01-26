package com.home.config;

import com.google.gson.Gson;
import com.home.util.CrowdConstant;
import com.home.util.CrowdUtil;
import com.home.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常处理
 *
 * @author 冯根源
 * @ControllerAdvice 表示当前类是一个基于注解的异常处理类
 * @date 2021/1/26 21:25
 */
@ControllerAdvice
public class CrowExceptionResolver {
    /**
     * 将一个具体的异常类型和一个方法关联起来
     *
     * @param exception
     * @return
     * @ExceptionHandler
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            //实际捕获到的异常类型
            NullPointerException exception,
            //当前请求对象
            HttpServletRequest request,
            //当前响应对象
            HttpServletResponse response) throws IOException {


        return commonResolve("system-error", exception, request, response);
    }

    /**
     * 将一个具体的异常类型和一个方法关联起来
     *
     * @param exception
     * @return
     * @ExceptionHandler
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveArithmeticException(
            //实际捕获到的异常类型
            NullPointerException exception,
            //当前请求对象
            HttpServletRequest request,
            //当前响应对象
            HttpServletResponse response) throws IOException {


        return commonResolve("system-error", exception, request, response);
    }

    /**
     * @param viewName  异常处理完成要去的页面
     * @param exception 实际驳货到的异常类型
     * @param request   当前请求对象
     * @param response  当前响应对象
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolve(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.判断当前请求的类型
        boolean judgeRequest = CrowdUtil.judgeRequestType(request);

        //2.如果是ajax请求
        if (judgeRequest) {
            //3.创建一个ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.error(exception.getMessage());
            //4.创建一个Gson对象
            Gson gson = new Gson();

            //5.将ResultEntity对象转化为JSON字符串
            String json = gson.toJson(resultEntity);

            //6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            //7.由于上面已经通过原生饿response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        //8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //9.将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
        //10.设置对应的视图
        modelAndView.setViewName(viewName);
        //11.返回modelAndView对象
        return modelAndView;
    }
}
