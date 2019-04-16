package com.lanxin.pandora.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.beans.UserBean;
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

    /**
     * 用户注册
     * @param request
     * @param response
     * @param account
     * @param password
     * @param name
     */
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

    /**
     * 用户登录
     * @param response
     * @param request
     * @param account
     * @param password
     */
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

    /**
     * 用户登出
     * @param response
     * @param id
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    public void logout(HttpServletResponse response, String id){
        userService.logout(id);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    /**
     * 获取用户数量（根据状态和关键字）
     * @param response
     * @param status
     * @param keyword
     */
    @RequestMapping(value = "/getCount.do", method = RequestMethod.POST)
    public void getCount(HttpServletResponse response, String status, String keyword) {
        new JsonResponse(response).write(JsonResponse.RES_OK, userService.count(status, keyword)+"", null);
    }

    /**
     * 获取用户列表（根据状态和关键字）
     * @param response
     * @param offset
     * @param limit
     * @param status
     * @param keyword
     */
    @RequestMapping(value = "/getList.do", method = RequestMethod.POST)
    public void getList(HttpServletResponse response, int offset, int limit, String status, String keyword) {
        new JsonResponse(response).write(userService.getList(offset, limit, status, keyword));
    }

    /**
     * 设置用户状态
     * @param response
     * @param id
     * @param status
     */
    @RequestMapping(value = "/setStatus.do", method = RequestMethod.POST)
    public void setStatus(HttpServletResponse response, String id, int status) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("status", status);
        userService.updateInfo(data);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    /**
     * 设置用户权限
     * @param response
     * @param id
     * @param role
     */
    @RequestMapping(value = "/setRole.do", method = RequestMethod.POST)
    public void setRole(HttpServletResponse response, String id, int role) {
        JsonResponse jr = new JsonResponse(response);
        UserBean user = userService.get(id);
        if (user != null) {
            role = user.getRole() + role;
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("role", role);
            userService.updateInfo(data);
            jr.write(JsonResponse.RES_OK);
        } else {
            jr.write(JsonResponse.RES_FAIL, null, "用户不存在");
        }
    }
}