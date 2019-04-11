package com.lanxin.pandora.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * 后台首页面
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "home/index";
    }

    /**
     * 栏目管理 - 页面
     * @return
     */
    @RequestMapping("/channel")
    public String channel() {
        return "home/channel";
    }

    /**
     * 内容管理页面
     * @param id
     * @param data
     * @return
     */
    @RequestMapping(value = "/content/id/{id}")
    public String content(@PathVariable String id, ModelMap data) {
        data.put("cid", id);
        return "home/content";
    }

    /**
     * 资源管理 - 页面
     * @param bid
     * @param data
     * @return
     */
    @RequestMapping(value = "/resource")
    public String resource(HttpServletRequest request, ModelMap data) {
        data.put("bid", request.getParameter("bid"));
        data.put("entry", request.getParameter("entry"));
        return "home/resource";
    }
}