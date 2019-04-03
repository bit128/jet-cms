package com.lanxin.pandora.controllers;

import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.service.ContentService;
import com.lanxin.pandora.tools.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private JsonResponse jsonResponse;

    @Autowired
    private ContentService contentService;

    @RequestMapping(value="/add.do",method=RequestMethod.POST)
    public void add(HttpServletResponse response, String fid, String title) {
        jsonResponse.setContext(response);
        jsonResponse.write(contentService.insert(fid, title));
    }

    @RequestMapping(value="/getList.do",method=RequestMethod.POST)
    public void getList(HttpServletResponse response, String fid, int offset, int limit) {
        jsonResponse.setContext(response);
        jsonResponse.write(contentService.getList(offset, limit, fid));
    }
}