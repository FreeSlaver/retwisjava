package com.song.retwis.tweet.vo;

import java.util.Date;

/**
 * Created by 00013708 on 2017/11/22.
 */
public class TweetVo {
    /**
     * 讲一个故事：
     * 谁在什么时候发送了一条什么内容的推特
     */
    private Long userId;
    //tweetId
    private Long id;

    private String content;

    private Date time;

    public TweetVo() {
    }

    public TweetVo(String content) {
        this.content = content;
        this.time = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
