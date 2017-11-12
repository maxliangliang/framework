package com.liang.sensitive.service.impl;

import com.alibaba.fastjson.JSON;
import com.liang.sensitive.dao.KeywordMapper;
import com.liang.sensitive.pojo.keyword.Keyword;
import com.liang.admin.pojo.sys.user.User;
import com.liang.sensitive.service.IKeywordService;
import com.liang.sensitive.service.IStopwordService;
import com.liang.common.util.keyword.KeywordFilter;
import com.liang.common.util.keyword.KeywordFilterBuilder;
import com.liang.common.util.keyword.ReplaceStrategy;
import com.liang.common.util.redis.RedisClient;
import com.liang.common.util.sys.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liang on 2017/4/30.
 */
@Service
public class KeywordServiceImpl implements IKeywordService{
    private Logger logger=Logger.getLogger( KeywordServiceImpl.class);


    @Autowired
    private KeywordMapper keywordMapper;

    @Autowired
    private IStopwordService stopwordService;

    @Autowired
    private RedisClient redisClient;


    /**
     * 统计用户传过来的内容中每个关键词出现过几次，
     * 先从redis中取出客户自定义的关键词和公共的关键词，如果出现错误就从数据库中取，保证功能不会出错
     * @param text
     * @return
     */
    @Override
    public Map<String,Integer> count(String userId, String text,Boolean needCommonKeyword) throws Exception{
        Map<String,Integer> resultMap=new HashMap<>();

        //获取
        List<String> userKeyword=Collections.emptyList();
        List<Character> userStopword=Collections.emptyList();

        if(!StringUtils.isBlank(userId)){
            //从redis中取
            try {
                //获取
                userKeyword = JSON.parseArray(redisClient.hget("keyword", userId), String.class);
                userStopword = JSON.parseArray(redisClient.hget("stopword", userId), Character.class);

                if(userKeyword ==null || userKeyword.isEmpty()){
                    logger.info(userId +" 的关键词在redis中不存在，去往数据库查询");
                    //从数据库中取
                    userKeyword = keywordMapper.findByUserId(userId);

                    logger.info(userId +" 的关键词正在保存到redis");
                    redisClient.hset("keyword",userId, JSON.toJSONString(userKeyword));
                }

                if(userStopword == null || userStopword.isEmpty()){
                    logger.info(userId +" 的停词在redis中不存在，去往数据库查询");
                    userStopword = stopwordService.findByUserId(userId);

                    logger.info(userId +" 的停词正在保存到redis");

                    redisClient.hset("stopword",userId,JSON.toJSONString(userStopword));
                }

            } catch (Exception e) {
                logger.error(e.toString());
                logger.error("redis 服务器连接出错，从数据库中读取数据");

                try {
                    //从数据库中取
                    userKeyword = keywordMapper.findByUserId(userId);

                    redisClient.hset("keyword",userId, JSON.toJSONString(userKeyword));

                    userStopword = stopwordService.findByUserId(userId);

                    redisClient.hset("stopword",userId,JSON.toJSONString(userStopword));
                } catch (Exception e1) {
                    //如果读取数据库出错，直接抛异常
                    logger.error(e1.toString());
                    logger.error("数据库中读取数据失败");
                    throw e1;
                }

            }

            KeywordFilter filter=this.buildFilter(userKeyword,userStopword,needCommonKeyword);

            if (filter.hasKeywords(text)){
                for (String word:userKeyword) {
                    int count=filter.count(text,word);

                    if(count>0){
                        resultMap.put(word,count);
                    }

                }

            }

            return resultMap;
        }

        //如果没有查到userId，则返回空map
        return resultMap;
    }

    /**
     * 返回替换关键字的字符串
     * @param userId
     * @param text
     * @param needCommonKeyword
     * @return
     * @throws Exception
     */
    @Override
    public String replaceText(String userId, String text, Boolean needCommonKeyword) throws Exception {
        //获取
        List<String> userKeyword = Collections.emptyList();
        List<Character> userStopword = Collections.emptyList();

        if (!StringUtils.isBlank(userId)) {
            //从redis中取
            try {
                //获取
                userKeyword = JSON.parseArray(redisClient.hget("keyword", userId), String.class);
                userStopword = JSON.parseArray(redisClient.hget("stopword", userId), Character.class);

                if(userKeyword ==null || userKeyword.isEmpty()){
                    logger.info(userId +" 的关键词在redis中不存在，去往数据库查询");
                    //从数据库中取
                    userKeyword = keywordMapper.findByUserId(userId);

                    logger.info(userId +" 的关键词正在保存到redis");
                    redisClient.hset("keyword",userId, JSON.toJSONString(userKeyword));
                }

                if(userStopword == null || userStopword.isEmpty()){
                    logger.info(userId +" 的停词在redis中不存在，去往数据库查询");
                    userStopword = stopwordService.findByUserId(userId);

                    logger.info(userId +" 的停词正在保存到redis");

                    redisClient.hset("stopword",userId,JSON.toJSONString(userStopword));
                }

            } catch (Exception e) {
                logger.error(e.getStackTrace());
                logger.error("redis 服务器连接出错，从数据库中读取数据");

                try {
                    //从数据库中取
                    userKeyword = keywordMapper.findByUserId(userId);

                    redisClient.hset("keyword",userId, JSON.toJSONString(userKeyword));

                    userStopword = stopwordService.findByUserId(userId);

                    redisClient.hset("stopword",userId,JSON.toJSONString(userStopword));
                } catch (Exception e1) {
                    //如果读取数据库出错，直接抛异常
                    logger.error(e1.getStackTrace());
                    logger.error("数据库中读取数据失败");
                    throw e1;
                }

            }

            KeywordFilter filter = this.buildFilter(userKeyword, userStopword, needCommonKeyword);

            //替换
            final ReplaceStrategy ss = new ReplaceStrategy() {
                @Override
                public String replaceWith(String keywords) {
                    String result="";

                    for (int i = 0; i < keywords.length(); i++) {
                        result+="*";
                    }
                    return result;
                }
            };

            //有用，这个是替换关键词的方法

            String result=filter.replace(text, ss);

            return result;

        }

        return null;
    }



    /**
     * 构建过滤器，其中包含管理员自定义的关键词和用户自定义的关键词
     * @param userKeyword 用户自定义的关键词
     * @param userStopword  用户自定义的跳过词
     * @param needCommonKeyword 是否需要通用过滤词
     * @return
     */
    private KeywordFilter buildFilter(List<String> userKeyword,List<Character> userStopword,Boolean needCommonKeyword){

        //构建过滤器
        KeywordFilterBuilder builder = new KeywordFilterBuilder();
        builder.setKeywords(userKeyword);
        builder.setSkipChars(userStopword);

        if(needCommonKeyword){
            //
            List<String> commonKeyword= Collections.emptyList();
            List<Character> commonStopword=Collections.emptyList();
            //从redis中取
            try {
                //获取
                commonKeyword= JSON.parseArray(redisClient.hget("keyword","admin"),String.class);
                commonStopword=JSON.parseArray(redisClient.hget("stopword","admin"),Character.class);

                if(commonKeyword ==null || commonKeyword.isEmpty()){
                    logger.info("admin 的关键词在redis中不存在，去往数据库查询");
                    //从数据库中取
                    commonKeyword = keywordMapper.findByUserId("1");

                    logger.info("admin 的关键词正在保存到redis");
                    redisClient.hset("keyword","admin", JSON.toJSONString(commonKeyword));
                }

                if(commonStopword == null || commonStopword.isEmpty()){
                    logger.info("admin 的停词在redis中不存在，去往数据库查询");
                    commonStopword = stopwordService.findByUserId("1");

                    logger.info("admin 的停词正在保存到redis");

                    redisClient.hset("stopword","admin",JSON.toJSONString(commonStopword));
                }

            }catch (Exception e){
                logger.error(e.toString());
                logger.error("redis 服务器连接出错，从数据库中读取数据");

                try {
                    //从数据库中取
                    commonKeyword = keywordMapper.findByUserId("1");
                    commonStopword = stopwordService.findByUserId("1");

                    redisClient.hset("keyword","admin", JSON.toJSONString(commonKeyword));
                    redisClient.hset("stopword","admin",JSON.toJSONString(commonStopword));


                }catch (Exception e1){
                    //如果读取数据库出错，直接抛异常
                    logger.error(e1.toString());
                    logger.error("数据库中读取数据失败");
                    throw e1;
                }

            }

            builder.addKeywords(commonKeyword);
            builder.addSkipwords(commonStopword);
        }


        KeywordFilter filter = builder.build();

        return filter;
    }


    /**
     * 添加关键词
     * @param keyword
     * @return
     */
    @Override
    public int addKeyword(Keyword keyword,User userEntity) {
        User user= userEntity;
        keyword.setId(UUIDUtil.get32UUID());
        keyword.setCreateTime(new Date());
        keyword.setOwner(user.getId());
        keyword.setIsDel("F");
        return keywordMapper.insert(keyword);
    }

    /**
     *逻辑删除关键词
     * @param keyword
     * @return
     */
    @Override
    public int delKeyword(Keyword keyword) {
        keyword.setIsDel("T");
        return keywordMapper.updateByPrimaryKey(keyword);
    }

    /**
     * 更新关键词
     * @param keyword
     * @return
     */
    @Override
    public int updateKeyword(Keyword keyword) {
        return keywordMapper.updateByPrimaryKey(keyword);
    }


}
