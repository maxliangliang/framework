package com.liang.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.pojo.sys.user.UserCriteria;
import com.liang.admin.service.IUserService;
import com.liang.common.util.sys.UUIDUtil;
import com.liang.admin.dao.UserMapper;
import com.liang.common.util.sys.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liang on 2017/4/29.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    //通过id查user
    @Override
    public User getUserById(String id) {
        User userEntity=userMapper.selectByPrimaryKey(id);
        return userEntity;
    }

    //通过用户名称或邮箱查询用户，用于用户的登录
    @Override
    public User getUserByUsername(String username) {
        UserCriteria criteria=new UserCriteria();
        //或者条件查询，
        criteria.or().andNameEqualTo(username);
        criteria.or().andEmailEqualTo(username);

        List<User> list=userMapper.selectByExample(criteria);

        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }

        return null;
    }


    /**
     * //判断登录的用户是否存在，同时判断用户名，密码和token,restful接口调用
     * @param userEntity
     * @return
     */
    @Override
    public User checkUser(User userEntity) {
        UserCriteria criteria=new UserCriteria();
        criteria.createCriteria().andNameEqualTo(userEntity.getName())
                .andPasswordEqualTo(MD5Util.getMD5Str(userEntity.getPassword()))
                .andTokenEqualTo(userEntity.getToken());

        List<User> list=userMapper.selectByExample(criteria);

        if(list!=null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }


    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    @Override
    public int addUser(User userEntity){
        userEntity.setId(UUIDUtil.get32UUID());
        userEntity.setIsDel("F");
        userEntity.setCreateDate(new Date());
        userEntity.setPassword(MD5Util.getMD5Str(userEntity.getPassword()));
        userEntity.setToken(UUIDUtil.get32UUID());

        return userMapper.insert(userEntity);
    }


    /**
     * 逻辑删除用户
     * @param userEntity
     * @return
     */
    @Override
    public int delUser(User userEntity){

        userEntity.setIsDel("T");
        return userMapper.updateByPrimaryKey(userEntity);
    }

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    @Override
    public int updateUser(User userEntity){

        return userMapper.updateByPrimaryKey(userEntity);
    }

    /**
     * 按条件分页查询
     * @param userEntity
     * @return
     */
    @Override
    public PageInfo<User> list(User userEntity,Integer pageNumber,Integer pageSize) {

        UserCriteria userCriteria=new UserCriteria();

        UserCriteria.Criteria criteria = userCriteria.createCriteria();

        if(!StringUtils.isBlank(userEntity.getName())){
            criteria.andNameLike("%"+userEntity.getName()+"%");
        }

        if(!StringUtils.isBlank(userEntity.getEmail())){
            criteria.andEmailLike("%"+userEntity.getEmail()+"%");
        }

        criteria.andIsDelEqualTo("F");

        //之后紧跟查询的语句，否则出现问题
        PageHelper.startPage(pageNumber,pageSize);

        List<User> list=userMapper.selectByExample(userCriteria);

        //将查出来的密码设为空，防止泄露
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPassword(null);
        }

        PageInfo<User> pageInfo=new PageInfo<User>(list);

        return pageInfo;
    }

}
