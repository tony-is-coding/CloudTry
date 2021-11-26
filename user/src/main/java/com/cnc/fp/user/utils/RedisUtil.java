package com.cnc.fp.user.utils;


import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

final public class RedisUtil {
    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public Boolean set(String key, Object data) {
        try {
            redisTemplate.opsForValue().set(key, data);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Boolean setEx(String key, Object data, Integer expire) {
        try {
            redisTemplate.opsForValue().set(key, data, expire, TimeUnit.SECONDS);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Boolean setNX(String key, Object data) {
        try {
            redisTemplate.opsForValue().setIfAbsent(key, data);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

}
