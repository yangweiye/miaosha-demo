package com.yangweiye.miaosha.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void cacheSet(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }


    public String cacheGet(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Long cacheDecr(String key) {
        return stringRedisTemplate.opsForValue().decrement(key);
    }
}
