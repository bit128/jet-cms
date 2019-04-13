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

    @Override
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

    @Override
    public int count(String bid, String keyword) {
        Criteria criteria = new Criteria();
        if (!bid.isEmpty()) {
            criteria.add("bid", bid);
        }
        if (!keyword.isEmpty()) {
            criteria.add("name", "%"+keyword+"%", "like", "AND");
        }
        return resourceMapper.count(criteria);
    }

    @Override
    public ResourceBean get(String id) {
        return resourceMapper.query(id);
    }

    @Override
    public List<ResourceBean> getList(int offset, int limit, String bid, String keyword) {
        Criteria criteria = new Criteria();
        if (!bid.isEmpty()) {
            criteria.add("bid", bid);
        }
        if (!keyword.isEmpty()) {
            criteria.add("name", "%"+keyword+"%", "like", "AND");
        }
        criteria.setOffset(offset);
        criteria.setLimit(limit);
        criteria.setOrder("sort desc");
        return resourceMapper.queryList(criteria);
    }

    @Override
    public void updateInfo(Map<String, Object> data) {
        data.put("time", Calendar.getInstance().getTimeInMillis() / 1000);
        resourceMapper.updateInfo(data);
    }

    @Override
    public void delete(String id) {
        resourceMapper.delete(id);
    }
}