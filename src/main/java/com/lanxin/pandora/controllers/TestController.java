package com.lanxin.pandora.controllers;

import java.util.HashMap;

import com.lanxin.pandora.tools.Curl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/register")
    public String register() {
        HashMap<String, String> params = new HashMap<>();
        params.put("account", "530521671@qq.com");
        params.put("password", "123456");
        params.put("name", "迦叶兰若");
        Curl curl = new Curl(Curl.POST, "http://127.0.0.1:8080/user/register.do");
        curl.setParams(params);
        return curl.sendRequest();
    }

    @RequestMapping("/login")
    public String login() {
        HashMap<String, String> params = new HashMap<>();
        params.put("account", "bit128");
        params.put("password", "123456");
        Curl curl = new Curl(Curl.POST, "http://127.0.0.1:8080/user/login.do");
        curl.setParams(params);
        return curl.sendRequest();
    }

    @RequestMapping("/logout")
    public String logout() {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", "16a1ad771ad11");
        Curl curl = new Curl(Curl.POST, "http://127.0.0.1:8080/user/logout.do");
        curl.setParams(params);
        return curl.sendRequest();
    }
}