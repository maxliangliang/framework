package com.liang.admin.service;

import com.liang.admin.pojo.sys.user.User;

/**
 * Created by liang on 2017/5/3.
 */
public interface ILoginService {

    User login(String username, String password);

}
