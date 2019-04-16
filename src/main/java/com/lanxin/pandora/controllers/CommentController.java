package com.lanxin.pandora.controllers;

import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.service.CommentService;
import com.lanxin.pandora.tools.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 提交评论
     * @param response
     * @param content
     * @param bid
     * @param uid
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public void add(HttpServletResponse response, String content, String bid, String uid) {
        new JsonResponse(response).write(commentService.insert(content, bid, uid));
    }
}