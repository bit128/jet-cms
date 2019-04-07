package com.lanxin.pandora.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ResourceBean;
import com.lanxin.pandora.mappers.ResourceMapper;
import com.lanxin.pandora.service.ResourceService;
import com.lanxin.pandora.tools.Criteria;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public String insert(String bid, String type, String path, long size) {
        int sort = 1;
        Map<String, Integer> map = resourceMapper.findNewSort(bid);
        if (map != null){
            sort = map.get("sort") + 1;
        }
        ResourceBean resource = new ResourceBean();
        resource.setId(DateTools.uniqid());
        resource.setBid(bid);
        resource.setType(type);
        resource.setPath(path);
        resource.setSize(size);
        resource.setSort(sort);
        resource.setTime(Calendar.getInstance().getTimeInMillis() / 1000);
        resourceMapper.insert(resource);
        return resource.getId();
    }

    public List<ResourceBean> getList(int offset, int limit, String bid) {
        Criteria criteria = new Criteria();
        criteria.add("bid", bid);
        criteria.setOffset(offset);
        criteria.setLimit(limit);
        criteria.setOrder("sort desc");
        return resourceMapper.queryList(criteria);
    }
}