package com.lanxin.pandora.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.beans.ResourceBean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ResourceService {
    public String insert(String bid, String type, String path, long size);
    public int count(String bid);
    public ResourceBean get(String id);
    public List<ResourceBean> getList(int offset, int limit, String bid);
    public void updateInfo(Map<String, Object> data);
    public void delete(String id);

    /**
     * 设置封面接口
     */
    public interface ImageOpt {
        @RequestMapping(value = "/setCover.do", method = RequestMethod.POST)
        public void setCover(HttpServletResponse response, String id, String path);
    }
}