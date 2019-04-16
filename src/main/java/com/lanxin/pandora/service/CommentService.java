package com.lanxin.pandora.service;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.CommentBean;

public interface CommentService {

    public final static int STATUS_HIDE = 1;
    public final static int STATUS_OPEN = 2;

    public String insert(String content, String bid, String uid);
    public int count(String bid, String status, String keyword);
    public CommentBean get(String id);
    public List<CommentBean> getList(int offset, int limit, String bid, String status, String keyword);
    public void updateInfo(Map<String, Object> data);
    public void delete(String id);
}