package com.lanxin.pandora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/content/id/{id}")
    public String content(@PathVariable String id, ModelMap data) {
        data.put("cid", id);
        return "home/content";
    }

    @RequestMapping(value = "/resource")
    public String resource(@RequestParam(value = "bid", required = false) String bid, ModelMap data) {
        data.put("bid", bid);
        return "home/resource";
    }
}