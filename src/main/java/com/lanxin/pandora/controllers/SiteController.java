package com.lanxin.pandora.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    //private final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Value("${pandora.upload.path}")
    private String uploadPath;

    @RequestMapping("/")
    public String index() {
        return "site/index";
    }

    @RequestMapping("/site/content")
    public String content() {
        return "site/content";
    }
}