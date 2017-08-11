package com.jgkj.plugin;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSecurityInterceptor implements HandlerInterceptor {
    private String localRequestUrl = "http://localhost:8080";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Logger.getRootLogger().info("preHandle1 ip:" + request.getRemoteAddr());

        System.out.println("the remote addr:" + request.getRemoteAddr());
        System.out.println("the local addr:" + request.getLocalAddr());
        System.out.println("the local port:" + request.getLocalPort());
        System.out.println("the requestUrl:" + request.getRequestURL());

        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.startsWith(localRequestUrl)) {
            //nginx转发过来都本地请求通过
            return true;//通过验证
        } else {
            //别处过来都请求，禁止
            return false;//通过验证
        }


//        Object obj = request.getSession().getAttribute("cur_user");
//        if (obj == null || !(obj instanceof UserEntity)) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return false;
//        }


        //return false; //阻止用户请求
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        Logger.getRootLogger().info("preHandle2");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        Logger.getRootLogger().info("afterCompletion:" + response.getStatus());
    }

}