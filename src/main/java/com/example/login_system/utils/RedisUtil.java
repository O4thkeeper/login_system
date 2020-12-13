package com.example.login_system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String key, String value, long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    public String getKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean checkValue(String key, String target) {
        String getKey = getKey(key);
        if (getKey == null) return false;
        return getKey.equals(target);
    }

}
