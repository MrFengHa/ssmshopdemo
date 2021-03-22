package com.home.config;

import com.home.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * 文件描述
 * <p>
 * Configuration springMVC的配置类注解
 * EnableWebSecurity   启用Web环境下权限控制功能
 *
 * @Author 冯根源
 * @create 2021/3/16 13:46
 */
@Configuration
@EnableWebSecurity
//启动全局方法控制功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()//对请求进行授权
                .antMatchers("/admin/to/login/page.html")
                .permitAll()
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/js/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                .antMatchers("/admin/get/page.html")
                .access("hasRole('经理') or hasAuthority('user:get')")
                .antMatchers("/admin/save.html")
                .hasAuthority("user:save")
                .antMatchers("/role/get/page/info.json")
                .hasRole("部长")
                .anyRequest()
                .authenticated()
                //权限不够前往的页面
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler(){

                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("exception",new Exception(CrowdConstant.MESSAGE_ACCESS_DENIED));
                        httpServletRequest.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(httpServletRequest,httpServletResponse);


                    }
                })
                .and().csrf().disable()
                .formLogin()//开启表单登录
                .loginPage("/admin/to/login/page.html")
                //处理登录请求的地址
                .loginProcessingUrl("/security/do/login.html")
                //指定登录成功后前往的地址
                .defaultSuccessUrl("/admin/to/main/page.html")
                //账号的请求参数名称
                .usernameParameter("loginAcct")
                //密码的请求参数名称
                .passwordParameter("userPswd")
                .and()
                .logout()           //开启退出功能
                //退出地址
                .logoutUrl("/security/do/logout.html")
                //指定退出成功以后前往的地址
                .logoutSuccessUrl("/admin/to/login/page.html")
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //临时使用内存登录模式测试代码
//        auth.inMemoryAuthentication().withUser("tom").password(getPasswordEncoder().encode("123123")).roles("ADMIN");

        //正式功能中使用基于数据库的认证
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
