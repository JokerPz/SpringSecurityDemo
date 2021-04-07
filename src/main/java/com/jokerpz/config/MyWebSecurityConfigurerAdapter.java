package com.jokerpz.config;

import com.jokerpz.exception.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 未认证成功时的url地址
                .loginPage("/showLogin")
                // 当发现什么url时，把请求转发给自定义的登录逻辑
                .loginProcessingUrl("/login")
                //  认证成功后转发的地址。 post 请求
                //.successForwardUrl("/showMain")
                // 认证成功后的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("/showMain");
                    }
                })
                // 认证失败转发的地址
                //.failureForwardUrl("/showFail")
                // 认证失败的处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("showFail");
                    }
                })

        ;

        http.authorizeRequests()
                .antMatchers("/showLogin", "/showFail").permitAll()
                .antMatchers("/abc").denyAll()
                .antMatchers("/demo").permitAll()
                .antMatchers("/authority").hasAnyAuthority("demo:update")
                .antMatchers("/role").hasRole("管理员")
                .antMatchers("/js/**").permitAll()
                .anyRequest()
                .authenticated();


        http.exceptionHandling()
                // 只适用于非前端方式，只适用于同步请求方式，所有异步（ajax）使用accessDeniedHandler
                .accessDeniedPage("/showAccessDenied");
                //.accessDeniedHandler(myAccessDeniedHandler);

        http.csrf().disable();
    }
}
