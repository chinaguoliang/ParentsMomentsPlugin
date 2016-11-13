package com.jgkj.plugin;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();

                //禁用CSRF保护
//        http.csrf().disable()
//                .authorizeRequests()
//                //任何访问都必须授权
//                .anyRequest().fullyAuthenticated()
//                //配置那些路径可以不用权限访问
//                .mvcMatchers("/attendance").permitAll()
//                .and()
//                .formLogin()













//                //登陆成功后的处理，因为是API的形式所以不用跳转页面
//                .successHandler(new RestAuthenticationSuccessHandler())
//                //登陆失败后的处理
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                //登出后的处理
//                .logout().logoutSuccessHandler(new RestLogoutSuccessHandler())
//                .and()
//                //认证不通过后的处理
//                .exceptionHandling()
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
        ;
    }
}