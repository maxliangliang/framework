package com.liang.admin.util.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by liang on 2017/4/25.
 */
@Component
public class RedisClient {

    private Logger logger=Logger.getLogger(RedisClient.class);

    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }finally {
            //返还到连接池
            if(null != jedis){
                jedis.close();
            }

        }
    }

    public String get(String key) throws Exception  {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            //返还到连接池
            if(null != jedis){
                jedis.close();

            }
        }
    }

    public void  hset(String key,String field,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key,field,value);
        }finally {
            if(null!=jedis){
                jedis.close();
            }
        }

    }

    public String hget(String key,String field){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key,field);
        }finally {
            if(null!=jedis){
                jedis.close();
            }
        }
    }

}
