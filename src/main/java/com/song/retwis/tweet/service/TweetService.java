package com.song.retwis.tweet.service;

import com.alibaba.fastjson.JSON;
import com.song.retwis.Utils;
import com.song.retwis.redis.RedisService;
import com.song.retwis.tweet.vo.TweetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by 00013708 on 2017/11/22.
 */
@Service
public class TweetService {

    @Autowired
    private RedisService<String, String> redisService;

    /**
     * redis有list，hash，set，sset，不对，我整个系统的架构，流程都没搞清楚，
     * 就直接来撸这个代码，不好
     **/
    public void add(Long uid, String content) {
        Long tweetId = redisService.increment("nextTweetId", 1);

        //还要try catch回滚这个id
        TweetVo tweetVo = new TweetVo(content);
        tweetVo.setUserId(uid);
        tweetVo.setId(tweetId);

        redisService.put(key(tweetId), JSON.toJSONString(tweetVo));

        //还要将这条tweet和用户建立关系
        redisService.zAdd("userTweets:" + uid, tweetId.toString(), System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public void del(Long tid,Long uid) {
        redisService.del(key(tid));
        //删掉此tweet和用户的关系
        redisService.zDel("userTweets:"+uid,tid.toString());
    }

    //查询一条tweet明细
    public TweetVo get(Long tid) {
        return JSON.parseObject(redisService.get(key(tid)), TweetVo.class);
    }

    //查询一个用户所有的tweet
    public List<TweetVo> list(Long uid) {
        Set<String> set = redisService.zReverseRange("userTweets:" + uid, 0L, System.currentTimeMillis());
        if (set == null || set.isEmpty()) {
            return null;
        }
        List<TweetVo> list = new ArrayList<>();
        for (String str : set) {//只是得到tweet的id
            String tweetStr = redisService.get(key(str));
            TweetVo tweetVo = JSON.parseObject(tweetStr, TweetVo.class);
            list.add(tweetVo);
        }
        return list;
    }

    public void edit(TweetVo tweetVo) {
        redisService.put(key(tweetVo.getId()), JSON.toJSONString(tweetVo));
    }

    private String key(String id) {
        return Utils.key("tweet", id);
    }

    private String key(Long id) {
        return key(id.toString());
    }


}
