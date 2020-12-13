package com.example.login_system;

import com.example.login_system.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void addTest(){
        redisUtil.setKey("137","1234",5);
    }

    @Test
    public void getTest(){
        System.out.println(redisUtil.getKey("137"));
    }
}
