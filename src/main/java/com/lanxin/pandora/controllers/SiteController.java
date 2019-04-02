package com.lanxin.pandora.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @RequestMapping("/")
    public String index() {
        return "hello, pandora!";
    }

    @RequestMapping("/site/version.page")
    public String version() {
        return "version: 1.0 alpha.";
    }
}