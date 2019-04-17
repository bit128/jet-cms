package com.lanxin.pandora.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.lanxin.pandora.service.UserService;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * 后台首页面
     * 
     * @return
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, ModelMap map) {
        if (request.getSession().getAttribute("uid") == null) {
            return "admin/login";
        } else {
            map.addAttribute("datetime", DateTools.dateNow("yyyy-MM-dd HH:mm"));
            map.addAttribute("timestamp", Calendar.getInstance().getTimeInMillis()/1000);
            return "home/index";
        }
    }

    /**
     * 栏目管理 - 页面
     * @return
     */
    @RequestMapping("/channel")
    public String channel(HttpServletRequest request) {
        if (userService.checkRole(request.getSession(), UserService.ROLE_CONTENT)) {
            return "home/channel";
        } else {
            return "home/no_role";
        }
    }

    /**
     * 内容管理页面
     * @param id
     * @param data
     * @return
     */
    @RequestMapping(value = "/content/id/{id}")
    public String content(HttpServletRequest request, @PathVariable String id, ModelMap data) {
        if (userService.checkRole(request.getSession(), UserService.ROLE_CONTENT)) {
            data.put("cid", id);
            return "home/content";
        } else {
            return "home/no_role";
        }
    }

    /**
     * 资源管理 - 页面
     * @param bid
     * @param data
     * @return
     */
    @RequestMapping(value = "/resource")
    public String resource(HttpServletRequest request, ModelMap data) {
        if (userService.checkRole(request.getSession(), UserService.ROLE_RESOURCE)) {
            data.put("bid", request.getParameter("bid"));
            data.put("entry", request.getParameter("entry"));
            return "home/resource";
        } else {
            return "home/no_role";
        }
    }

    /**
     * 用户管理 - 页面
     * @return
     */
    @RequestMapping(value = "/user")
    public String user(HttpServletRequest request) {
        if (userService.checkRole(request.getSession(), UserService.ROLE_USER)) {
            return "home/user";
        } else {
            return "home/no_role";
        }
    }

    /**
     * 评论管理 - 页面
     * @return
     */
    @RequestMapping(value = "/comment")
    public String comment() {
        return "home/comment";
    }
}