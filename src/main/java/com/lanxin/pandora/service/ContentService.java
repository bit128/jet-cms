package com.lanxin.pandora.service;

import java.util.List;

import com.lanxin.pandora.beans.ContentBean;

public interface ContentService {
    public String insert(String fid, String title);
    public ContentBean get(String id);
    public List<ContentBean> getList(int offset, int limit, String fid);
}