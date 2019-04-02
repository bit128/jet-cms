package com.lanxin.pandora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("")
    public String index() {
        return "home/index";
    }

    @RequestMapping("/channel")
    public String channel() {
        return "home/channel";
    }
}