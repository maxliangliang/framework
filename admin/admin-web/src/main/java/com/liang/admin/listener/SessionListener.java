package com.liang.admin.listener;


import com.liang.admin.pojo.sys.user.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by liang on 2017/5/17.
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        User userEntity=(User) httpSessionEvent.getSession().getAttribute("user");
        if(userEntity != null && userEntity.getName().equals("admin")){
            System.out.println("session失效了");
        }
    }
}
