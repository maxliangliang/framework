package com.liang.admin.service;


import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.pojo.sensitive.keyword.Keyword;

import java.util.Map;

/**
 * Created by liang on 2017/4/30.
 */
public interface IKeywordService {

    //查找传过来的内容中每个关键词出现过几次
    Map<String,Integer> count(String userId, String text, Boolean commonKeyword) throws Exception;

    //返回替换完的文本
    String replaceText(String userId, String text, Boolean commonKeyword) throws Exception;

    //添加关键词
    int addKeyword(Keyword keyword, User user);

    //删除关键词
    int delKeyword(Keyword keyword);

    //更新关键词
    int updateKeyword(Keyword keyword);


}
