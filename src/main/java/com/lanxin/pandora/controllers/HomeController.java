package com.lanxin.pandora.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.lanxin.pandora.tools.DateTools;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * 后台首页面
     * @return
     */
    @RequestMapping("")
    public String index(ModelMap map) {
        map.addAttribute("datetime", DateTools.dateNow("yyyy-MM-dd HH:mm"));
        map.addAttribute("timestamp", Calendar.getInstance().getTimeInMillis()/1000);
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

    /**
     * 用户管理 - 页面
     * @return
     */
    @RequestMapping(value = "/user")
    public String user() {
        return "home/user";
    }
}