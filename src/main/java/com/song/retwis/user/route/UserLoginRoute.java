package com.song.retwis.user.route;

import com.song.retwis.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by 00013708 on 2017/11/9.
 */
@Service
public class UserLoginRoute implements Route {

    @Autowired
    private UserService userService;

    @Override
    public Object handle(Request request, Response response) throws Exception {


        return null;
    }
}
