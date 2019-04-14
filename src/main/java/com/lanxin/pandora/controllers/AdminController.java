package com.lanxin.pandora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "admin/login";
    }
}