package com.lanxin.pandora.service;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ContentBean;

public interface ContentService {
    public String insert(String fid, String title);
    public int count(String fid, String keyword);
    public ContentBean get(String id);
    public List<String> getBreadcrumb(String id, int level);
    public List<ContentBean> getSimpleList(int offset, int limit, String fid, String keyword);
    public void updateInfo(Map<String, Object> data);
    public void delete(String id);
}