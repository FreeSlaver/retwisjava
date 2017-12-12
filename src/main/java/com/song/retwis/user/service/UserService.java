package com.song.retwis.user.service;

import com.alibaba.fastjson.JSON;
import com.song.retwis.redis.RedisService;
import com.song.retwis.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 00013708 on 2017/11/22.
 */
@Service
public class UserService {

    @Autowired
    private RedisService<String, String> redisService;

    public void add(UserVo uservo) {
        Long uid = redisService.increment("nextUserId", 1);
        uservo.setId(uid);
        redisService.put(uid.toString(), JSON.toJSONString(uservo));
    }
    //删除用户是直接删除，还是改状态？感觉改状态要好些？
    public void del(Long id) {
        redisService.del(getUserKey(id));
    }


    private String getUserKey(Long id) {
        return new StringBuilder("user:").append(id).toString();
    }

    public UserVo get(Long id) {
        return JSON.parseObject(redisService.get(getUserKey(id)), UserVo.class);
    }


}
