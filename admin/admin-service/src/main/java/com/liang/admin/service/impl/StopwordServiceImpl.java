package com.liang.admin.service.impl;

import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.dao.StopwordMapper;
import com.liang.admin.pojo.sensitive.stopword.Stopword;
import com.liang.admin.service.IStopwordService;
import com.liang.common.util.sys.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liang on 2017/4/30.
 */
@Service
public class StopwordServiceImpl implements IStopwordService {

    @Autowired
    private StopwordMapper stopwordMapper;


    /**
     * 根据用户id查找跳过词
     * @param userId
     * @return
     */
    @Override
    public List<Character> findByUserId(String userId) {

        List<Character> list= stopwordMapper.selectByUserId(userId);

        return list;
    }

    @Override
    public int addStopword(Stopword stopword, User userEntity) {
        User user= userEntity;

        stopword.setId(UUIDUtil.get32UUID());
        stopword.setIsDel("F");
        stopword.setOwner(user.getId());
        stopword.setCreateTime(new Date());
        return stopwordMapper.insert(stopword);
    }

    @Override
    public int delStopword(Stopword stopword) {
        return 0;
    }

    @Override
    public int updateStopword(Stopword stopword) {
        return 0;
    }

}
