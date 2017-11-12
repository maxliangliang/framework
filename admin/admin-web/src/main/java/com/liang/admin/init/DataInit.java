package com.liang.admin.init;

import com.liang.admin.service.IKeywordService;
import com.liang.admin.service.IStopwordService;
import com.liang.admin.util.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。
 * 也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。
 * Created by liang on 2017/5/1.
 */

@Component
@Order(value = 1)//启动时候加载顺序
public class DataInit implements CommandLineRunner {

    @Autowired
    private IKeywordService keywordService;

    @Autowired
    private IStopwordService stopwordService;

    @Autowired
    private RedisClient redisClient;

    @Override
    public void run(String... strings) throws Exception {



    }
}
