package com.liang.admin.service;


import com.github.pagehelper.PageInfo;
import com.liang.admin.pojo.sys.user.User;

import java.util.List;

/**
 * Created by liang on 2017/4/29.
 */
public interface IUserService {

    //通过id查user
    User getUserById(String id);

    //通过username查询用户,这个username可能是用户名，或者邮箱
    User getUserByUsername(String username);


    //判断登录的用户是否存在，同时判断用户名，密码和token
    //这个方法是用来restful接口的验证
    User checkUser(User userEntity);

    //添加用户
    int addUser(User userEntity);

    //删除用户，这里只是逻辑删除
    int delUser(User userEntity);

    //更新用户内容
    int updateUser(User userEntity);

    //按照条件分页查询
    PageInfo<User> list(User userEntity,Integer pageNumber,Integer pageSize);



}
