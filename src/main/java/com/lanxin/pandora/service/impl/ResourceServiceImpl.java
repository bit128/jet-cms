package com.lanxin.pandora.service.impl;

import java.util.HashMap;

import com.lanxin.pandora.beans.ResourceBean;
import com.lanxin.pandora.mappers.ResourceMapper;
import com.lanxin.pandora.service.ResourceService;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public String insert(String bid, String type, String path, long size) {
        ResourceBean resource = new ResourceBean();
        resource.setId(DateTools.uniqid());
        resource.setBid(bid);
        resource.setType(type);
        resource.setPath(path);
        resource.setSize(size);
        resourceMapper.insert(resource);
        return resource.getId();
    }
}