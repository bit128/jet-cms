package com.lanxin.pandora.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    private final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Value("${pandora.upload.path}")
    private String uploadPath;

    @RequestMapping("/")
    public String index() {
        log.info("打印日志");
        return "jet-CMS.";
    }
}