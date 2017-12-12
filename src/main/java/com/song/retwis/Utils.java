package com.song.retwis;

/**
 * Created by 00013708 on 2017/11/22.
 */
public class Utils {

    public static String key(String prefix, String id) {
        return new StringBuilder(prefix).append(":").append(id).toString();
    }
}
