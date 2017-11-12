package com.liang.admin.filter;

import com.alibaba.fastjson.JSON;
import com.liang.admin.pojo.JsonResult;
import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤rest接口的非法用户，这里只过滤wordfilter这个请求地址的请求
 * Created by liang on 2017/4/30.
 */
@WebFilter(filterName = "userFilter",urlPatterns = "/wordfilter/*")
public class RestUserFilter implements Filter {
    @Autowired
    private IUserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username=servletRequest.getParameter("username");
        String password=servletRequest.getParameter("password");
        String token=servletRequest.getParameter("token");
        String text=servletRequest.getParameter("text");

        JsonResult jsonResult=new JsonResult();

        if(StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(token)
                || StringUtils.isBlank(text)){

            jsonResult.setData(null);
            jsonResult.setStatus(400);
            jsonResult.setMsg("非法请求");

            servletResponse.setContentType("application/json;charset=utf-8");

            servletResponse.getWriter().write(JSON.toJSONString(jsonResult));

            return;
        }


        User userEntity=new User();
        userEntity.setName(username);
        userEntity.setPassword(password);
        userEntity.setToken(token);

        //检查用户是否存在
        User user=userService.checkUser(userEntity);

        if(user == null){
            jsonResult.setStatus(700);
            jsonResult.setData(null);
            jsonResult.setResultCount(0);
            jsonResult.setMsg("用户名或密码错误");

            servletResponse.setContentType("application/json;charset=utf-8");

            servletResponse.getWriter().write(JSON.toJSONString(jsonResult));
            return ;
        }else if(user !=null && user.getIsDel().equals("T")){
            jsonResult.setStatus(701);
            jsonResult.setData(null);
            jsonResult.setResultCount(0);
            jsonResult.setMsg("用户已被锁定");

            servletResponse.setContentType("application/json;charset=utf-8");

            servletResponse.getWriter().write(JSON.toJSONString(jsonResult));

            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
