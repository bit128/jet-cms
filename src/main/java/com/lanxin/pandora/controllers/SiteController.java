package com.lanxin.pandora.controllers;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.mappers.ContentMapper;
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

    @RequestMapping("test")
    public String test() {
        ContentBean content = contentService.getContent("169dddea4ecfb");
        if (content != null) {
            return content.getTitle();
        }
        return "not found";
    }
}