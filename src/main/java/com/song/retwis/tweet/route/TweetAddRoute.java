package com.song.retwis.tweet.route;

import com.alibaba.fastjson.JSON;
import com.song.retwis.template.service.TemplateService;
import com.song.retwis.tweet.service.TweetService;
import com.song.retwis.tweet.vo.TweetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by 00013708 on 2017/11/22.
 */
@Service
public class TweetAddRoute implements Route {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TemplateService templateService;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        Map<String, Object> map = JSON.parseObject(body);
        Long uid = Long.valueOf(String.valueOf(map.get("uid")));
        String content = (String) map.get("newTweet");
//        Long uid = Long.valueOf(request.queryParams("uid"));
//        String content = request.queryParams("newTweet");
        tweetService.add(uid, content);

        List<TweetVo> list = tweetService.list(uid);
        return templateService.renderTweets(list);
    }
}

