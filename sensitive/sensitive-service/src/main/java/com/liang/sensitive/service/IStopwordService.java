package com.liang.sensitive.service;


import com.liang.sensitive.pojo.stopword.Stopword;
import com.liang.admin.pojo.sys.user.User;

import java.util.List;

/**
 * Created by liang on 2017/4/30.
 */
public interface IStopwordService {
    //根据user查找对应的跳过词，不分页
    List<Character> findByUserId(String userId);

    //添加跳过词
    int addStopword(Stopword stopword, User user);

    //删除跳过词
    int delStopword(Stopword stopword);

    //更新跳过词
    int updateStopword(Stopword stopword);

}
