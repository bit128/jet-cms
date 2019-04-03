package com.lanxin.pandora.controllers;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/")
    public String index() {
        return "hello, pandora!";
    }

    @RequestMapping("/site/version.page")
    public String version() {
        return "version: 1.0 alpha.";
    }

    /*
    @RequestMapping("insert")
    public String newContent() {
        return contentService.newContent("", "新建栏目内容");
    }*/

    @RequestMapping("test")
    public String test() {
        ContentBean content = contentService.get("169e0ef0bf15f");
        if (content != null) {
            return content.getTitle();
        }
        return "not found";
    }
}