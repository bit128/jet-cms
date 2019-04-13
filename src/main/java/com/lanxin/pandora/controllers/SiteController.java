package com.lanxin.pandora.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {


    @Value("${pandora.upload.path}")
    private String uploadPath;

    @RequestMapping("/")
    public String index() {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd/");
        return format.format(new Date());
    }

    @RequestMapping("/site/version.page")
    public String version() {
        return "version: 1.0 alpha:" + uploadPath;
    }

    /*
    @RequestMapping("insert")
    public String newContent() {
        return contentService.newContent("", "新建栏目内容");
    }*/

    @RequestMapping("/test")
    public String test() {
        String password = "123";
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}