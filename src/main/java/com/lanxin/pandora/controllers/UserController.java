package com.lanxin.pandora.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.service.UserService;
import com.lanxin.pandora.tools.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, String account, String password, String name) {
        JsonResponse jr = new JsonResponse(response);
        if (account.trim().isEmpty()) {
            jr.write(JsonResponse.RES_FAIL, null, "账号不能为空");
        } else {
            if (userService.existAccount(account)) {
                jr.write(JsonResponse.RES_FAIL, null, "账号重名");
            } else {
                String ip = request.getRemoteAddr();
                jr.write(userService.insert(account, password, name, ip));
            }
        }
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public void login(HttpServletResponse response, HttpServletRequest request, String account, String password) {
        JsonResponse jr = new JsonResponse(response);
        if (account.trim().isEmpty()) {
            jr.write(JsonResponse.RES_FAIL, null, "账号不能为空");
        } else {
            String ip = request.getRemoteAddr();
            jr.write(userService.login(account, password, ip));
        }
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    public void logout(HttpServletResponse response, String id){
        userService.logout(id);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    @RequestMapping(value = "/getCount.do", method = RequestMethod.POST)
    public void getCount(HttpServletResponse response, String status, String keyword) {
        new JsonResponse(response).write(JsonResponse.RES_OK, userService.count(status, keyword)+"", null);
    }

    @RequestMapping(value = "/getList.do", method = RequestMethod.POST)
    public void getList(HttpServletResponse response, int offset, int limit, String status, String keyword) {
        new JsonResponse(response).write(userService.getList(offset, limit, status, keyword));
    }
}