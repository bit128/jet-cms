package com.lanxin.pandora.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.service.ContentService;
import com.lanxin.pandora.tools.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 新建内容
     * @param response
     * @param fid
     * @param title
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public void add(HttpServletResponse response, String fid, String title) {
        new JsonResponse(response).write(contentService.insert(fid, title));
    }

    /**
     * 获取内容级联菜单
     * @param response
     * @param id
     * @param level
     */
    @RequestMapping(value = "/getBreadcrumb.do", method = RequestMethod.POST)
    public void getBreadcrumb(HttpServletResponse response, String id, int level) {
        new JsonResponse(response).write(contentService.getBreadcrumb(id, level));
    }

    /**
     * 根据父级id获取内容数量
     * @param response
     * @param fid
     */
    @RequestMapping(value = "/getCount.do", method = RequestMethod.POST)
    public void getItemCount(HttpServletResponse response, String fid) {
        new JsonResponse(response).write(JsonResponse.RES_OK, contentService.count(fid)+"", null);
    }

    /**
     * 获取内容详情
     * @param response
     * @param id
     */
    @RequestMapping(value = "/get.do/id/{id}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String id) {
        ContentBean content = contentService.get(id);
        if (content != null) {
            new JsonResponse(response).write(content);
        } else {
            new JsonResponse(response).write(JsonResponse.RES_FAIL, null, "内容不存在");
        }
    }

    /**
     * 获取简单内容列表
     * @param response
     * @param fid
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/getSimpleList.do", method = RequestMethod.POST)
    public void getList(HttpServletResponse response, String fid, int offset, int limit) {
        new JsonResponse(response).write(contentService.getSimpleList(offset, limit, fid));
    }

    /**
     * 修改内容
     * @param request
     * @param response
     */
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

    /**
     * 修改内容细节
     * @param response
     * @param id
     * @param detail
     */
    @RequestMapping(value = "/saveDetail.do", method = RequestMethod.POST)
    public void updateAttribute(HttpServletResponse response, String id, String detail){
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("detail", detail);
        contentService.updateInfo(data);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    /**
     * 设置内容状态
     * @param response
     * @param id
     * @param status
     */
    @RequestMapping(value = "/setStatus.do", method = RequestMethod.POST)
    public void setStatus(HttpServletResponse response, String id, int status) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("status", status);
        contentService.updateInfo(data);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }

    /**
     * 删除内容
     * @param response
     * @param id
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, String id) {
        contentService.delete(id);
        new JsonResponse(response).write(JsonResponse.RES_OK);
    }
}