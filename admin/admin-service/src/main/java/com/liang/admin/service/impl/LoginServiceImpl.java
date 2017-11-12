package com.liang.admin.service.impl;

import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.service.ILoginService;
import com.liang.admin.service.IUserService;
import com.liang.common.util.sys.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liang on 2017/5/3.
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserService userService;

    /**
     * 验证登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        User user=userService.getUserByUsername(username);

        if(user != null ){
            if(MD5Util.getMD5Str(password).equalsIgnoreCase(user.getPassword())){
                return user;
            }

        }

        return null;
    }

}
