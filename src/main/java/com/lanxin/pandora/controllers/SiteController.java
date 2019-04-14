package com.lanxin.pandora.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {


    @Value("${pandora.upload.path}")
    private String uploadPath;

    @RequestMapping("/")
    public String index() {
        return "jet-CMS.";
    }
}