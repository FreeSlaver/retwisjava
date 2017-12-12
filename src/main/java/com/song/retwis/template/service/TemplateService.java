package com.song.retwis.template.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.song.retwis.tweet.service.TweetService;
import com.song.retwis.tweet.vo.TweetVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.ModelAndView;
import spark.template.jtwig.JtwigTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 00013708 on 2017/11/22.
 */
@Service
public class TemplateService {
    @Autowired
    private TweetService tweetService;

    public String renderTweets(List<TweetVo> list) {
        Map model = new HashMap();
        model.put("tweetList", list);

        ModelAndView mav = new ModelAndView(model, "tweetList.twig");
//        没有添加注解的类怎么也搞成spring的？
        return new JtwigTemplateEngine().render(mav);
    }

    /**
     * 如果是请求首页，应该是返回的index.twig
     */
    public String renderIndex(List<TweetVo> list) {
        Map model = new HashMap();
        model.put("tweetList", list);
        ModelAndView mav = new ModelAndView(model, "index.twig");
        return new JtwigTemplateEngine().render(mav);
    }

    public String renderResume() throws IOException {
        String path = "templates/blue/resume.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        String json = FileUtils.readFileToString(file, "UTF-8");
        Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
        });

        ModelAndView mav = new ModelAndView(map, "blue/index.twig");
        return new JtwigTemplateEngine().render(mav);
    }
}
