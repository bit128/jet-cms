package com.lanxin.pandora.service;

import java.util.List;

import com.lanxin.pandora.beans.ResourceBean;

public interface ResourceService {
    public String insert(String bid, String type, String path, long size);
    public List<ResourceBean> getList(int offset, int limit, String bid);
}