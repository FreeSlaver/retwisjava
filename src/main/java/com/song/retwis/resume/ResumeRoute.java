package com.song.retwis.resume;

import com.song.retwis.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by 00013708 on 2017/12/4.
 */
@Service
public class ResumeRoute implements Route {

    @Autowired
    private TemplateService templateService;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return templateService.renderResume();
    }
}
