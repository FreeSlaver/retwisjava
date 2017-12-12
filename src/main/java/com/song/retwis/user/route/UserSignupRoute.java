package com.song.retwis.user.route;

import com.song.retwis.redis.RedisService;
import com.song.retwis.vo.RespVo;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 00013708 on 2017/11/9.
 */
@Service
public class UserSignupRoute implements Route {

    @Autowired
    private RedisService<String, String> redisService;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        //是怎么从里面判断这个userName是否重复？
        //redisService.hget();
        String hVal = redisService.hget("users", username);
        if (StringUtils.isNotEmpty(hVal)) {
            return new RespVo(1, "username exist");
        }
        long nextUserId = redisService.increment("nextUserId", 1);
        String userKey = "user:" + nextUserId;
        //password这里应该加密
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("username",username);
        String md5Pwd = Md5Crypt.md5Crypt(password.getBytes());
        userMap.put("password",md5Pwd);
        redisService.hmput(userKey, userMap);

        //将userId和username关联起来
        redisService.hput("users",username,String.valueOf(nextUserId));

        //还要try。。catch，然后decrment，其实就是increment -1
        return new RespVo();
    }
}
