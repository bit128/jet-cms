package com.lanxin.pandora.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
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
    private ContentService contentService;

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public void add(HttpServletResponse response, String fid, String title) {
        new JsonResponse(response).write(contentService.insert(fid, title));
    }

    @RequestMapping(value = "/getBreadcrumb.do", method = RequestMethod.POST)
    public void getBreadcrumb(HttpServletResponse response, String id, int level) {
        new JsonResponse(response).write(contentService.getBreadcrumb(id, level));
    }

    @RequestMapping(value = "/getCount.do", method = RequestMethod.POST)
    public void getItemCount(HttpServletResponse response, String fid) {
        new JsonResponse(response).write(JsonResponse.RES_OK, contentService.count(fid)+"", null);
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.GET)
    public void get(HttpServletResponse response, String id) {
        new JsonResponse(response).write(contentService.get(id));
    }

    @RequestMapping(value = "/getSimpleList.do", method = RequestMethod.POST)
    public void getList(HttpServletResponse response, String fid, int offset, int limit) {
        new JsonResponse(response).write(contentService.getSimpleList(offset, limit, fid));
    }

    @RequestMapping(value = "/updateInfo.do", method = RequestMethod.POST)
    public void updateInfo(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", request.getParameter("id"));
        data.put("fid", request.getParameter("fid"));
        data.put("title", request.getParameter("title"));
        data.put("keyword", request.getParameter("keyword"));
        data.put("sort", request.getParameter("sort"));
        data.put("data", request.getParameter("data"));
        contentService.updateInfo(data);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    @RequestMapping(value = "/setStatus.do", method = RequestMethod.POST)
    public void setStatus(HttpServletResponse response, String id, int status) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("status", status);
        contentService.updateInfo(data);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, String id) {
        contentService.delete(id);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }
}