package com.lanxin.pandora.service;

import com.lanxin.pandora.beans.ContentBean;

public interface ContentService {
    public String newContent(String fid, String defaultTitle);
    public ContentBean getContent(String id);
}