package com.liang.admin.interceptor;

import com.liang.admin.pojo.sys.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截，如果没有登录，就跳到登录页
 * Created by liang on 2017/5/14.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag;
        User userEntity=(User) httpServletRequest.getSession().getAttribute("user");

        if(null==userEntity){
            //跳到错误页面，让错误页面判断跳向别的页面，为了解决session失效后div中登录页面相互嵌套的问题
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/error/403.do");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
