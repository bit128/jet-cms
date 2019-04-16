package com.lanxin.pandora.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanxin.pandora.beans.UserBean;
import com.lanxin.pandora.mappers.UserMapper;
import com.lanxin.pandora.service.UserService;
import com.lanxin.pandora.tools.DateTools;
import com.lanxin.pandora.tools.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 管理员登录-页面
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 管理员登录
     * @param request
     * @param response
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        JsonResponse jr = new JsonResponse(response);
        String account = request.getParameter("account");
        String password = DigestUtils.md5DigestAsHex(request.getParameter("password").getBytes());
        if (!account.isEmpty()) {
            UserBean user = userMapper.queryByAccount(account);
            if (user != null && password.equals(user.getPassword()) && user.getStatus() == UserService.STATUS_ADMIN) {
                //缓存会话
                HttpSession session = request.getSession();
                session.setAttribute("uid", user.getId());
                session.setAttribute("role", user.getRole());
                session.setAttribute("name", user.getName());
                session.setAttribute("activeTime", DateTools.date(user.getActiveTime(), "yyyy-MM-dd HH:mm"));
                session.setAttribute("ip", user.getIp());
                //更新登录记录
                HashMap<String, Object> data = new HashMap<>();
                data.put("id", user.getId());
                data.put("activeTime", Calendar.getInstance().getTimeInMillis()/1000);
                data.put("ip", request.getRemoteAddr());
                userMapper.updateInfo(data);
                jr.write(JsonResponse.RES_OK);
            } else {
                jr.write(JsonResponse.RES_FAIL, null, "账号不存在或者无权限.");
            }
        } else {
            jr.write(JsonResponse.RES_FAIL, null, "账号名不能为空.");
        }
    }

    /**
     * 管理与登出
     * @param request
     * @param response
     */
    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("uid");
        session.removeAttribute("role");
        session.removeAttribute("name");
        session.removeAttribute("activeTime");
        session.removeAttribute("ip");
        try {
            response.sendRedirect("/admin/loginPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}