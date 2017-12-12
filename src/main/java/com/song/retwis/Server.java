package com.song.retwis;

import com.song.retwis.resume.ResumeRoute;
import com.song.retwis.template.service.TemplateService;
import com.song.retwis.tweet.route.TweetAddRoute;
import com.song.retwis.tweet.route.TweetDelRoute;
import com.song.retwis.tweet.route.TweetEditRoute;
import com.song.retwis.tweet.service.TweetService;
import com.song.retwis.tweet.vo.TweetVo;
import com.song.retwis.user.route.UserLoginRoute;
import com.song.retwis.user.route.UserSignupRoute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spark.Spark;

import java.util.List;

import static spark.Spark.staticFiles;

/**
 * Created by 00013708 on 2017/11/9.
 */
public class Server {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ConfigService configService = context.getBean(ConfigService.class);
        Spark.port(configService.getServerPort());
        Spark.threadPool(configService.getMaxThreads(), configService.getMinThreads(),
                configService.getIdleTimeoutMillis());

        staticFiles.location("/public");
        Spark.get("/ping", (req, res) -> "pong");

        TweetService tweetService = context.getBean(TweetService.class);
        TemplateService templateService = context.getBean(TemplateService.class);
        //user
        Spark.post("/user/login", context.getBean(UserLoginRoute.class));
        Spark.post("/user/signup", context.getBean(UserSignupRoute.class));

        //tweet
        Spark.get("/", (req, res) -> {
            List<TweetVo> list = tweetService.list(1L);
            return templateService.renderIndex(list);
        });
        Spark.post("/tweet/add", context.getBean(TweetAddRoute.class));
        Spark.post("/tweet/del", context.getBean(TweetDelRoute.class));
        Spark.post("/tweet/edit", context.getBean(TweetEditRoute.class));

        //resume
        Spark.get("/resume", context.getBean(ResumeRoute.class));

    }
}
