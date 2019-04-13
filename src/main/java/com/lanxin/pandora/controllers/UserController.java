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
}