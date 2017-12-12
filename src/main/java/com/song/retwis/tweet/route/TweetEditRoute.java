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
 * Created by 00013708 on 2017/11/23.
 */
@Service
public class TweetEditRoute implements Route {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TemplateService templateService;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        TweetVo tweetVo = JSON.parseObject(body, TweetVo.class);

        tweetService.edit(tweetVo);
        //这时应该返回的什么了？全部的tweet？明显不对，
        /**
         * 直接发送自己的大脑，不一定非要看别人的参考，抄袭。
         * 比如，我现在删除，其本质就是将要删除的id发给后台，后台删除成功，就将这个dom给移除掉；
         * 失败，就提示错误信息等。
         * 现在编辑，编辑就是发给后台新的数据内容，进行update，update成功之后，将这个被改的内容改变下就行了。
         * 那么怎样标示当前元素了？通过唯一的id，然后html的dom中的元素的值是此id？
         * 错误的，因为如果只是改变用户修改的东西，而之前的页面都是静态的，但是有可能其他用户也进行了修改。
         * 如果只用js，返回的结果就不会包含这种变化。
         */
        Long uid = tweetVo.getUserId();
        return templateService.renderTweets(tweetService.list(uid));

    }
}
