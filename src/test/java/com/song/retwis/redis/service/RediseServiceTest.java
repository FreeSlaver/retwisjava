package com.song.retwis.redis.service;

import com.song.retwis.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by 00013708 on 2017/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class RediseServiceTest {

    @Autowired
    private RedisService<String,String> redisService;

    @Test
    public void zsetTest(){
        Set<String> set =  redisService.zGetByScore("userTweets:1" , 0L, System.currentTimeMillis());
        System.out.println(set.size());
    }
}
