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

/**
 * Created by 00013708 on 2017/11/22.
 */
@Service
public class TweetDelRoute implements Route {

    @Autowired
    private TemplateService templateService;
    @Autowired
    private TweetService tweetService;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        String tid = String.valueOf(JSON.parseObject(body).get("tid"));
        TweetVo tweetVo = tweetService.get(Long.valueOf(tid));
        Long uid = tweetVo.getUserId();
        //删掉此tweet
        tweetService.del(Long.valueOf(tid),uid);

        return templateService.renderTweets(tweetService.list(Long.valueOf(uid)));
    }
}
