package com.jgkj.plugin;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            String errorJson = "{\"code\":6666,\"msg\":\"error\"}";
            responseOutWithJson(response,errorJson);
            //别处过来都请求，禁止
            return false;
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


    /**
     * 以JSON格式输出
     * @param response
     */
    protected void responseOutWithJson(HttpServletResponse response,
                                       String responseObject) {
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}