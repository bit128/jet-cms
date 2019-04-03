package com.lanxin.pandora.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.mappers.ContentMapper;
import com.lanxin.pandora.service.ContentService;
import com.lanxin.pandora.tools.Criteria;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public String insert(String fid, String title) {
        int sort = 1;
        Map<String, Integer> map = contentMapper.findNewSort(fid);
        if (map != null){
            sort = map.get("sort") + 1;
        }
        ContentBean content = new ContentBean();
        content.setId(DateTools.uniqid());
        content.setFid(fid);
        content.setTitle(title);
        content.setCreateTime(Calendar.getInstance().getTimeInMillis()/1000);
        content.setSort(sort);
        contentMapper.insert(content);
        return content.getId();
    }

    @Override
    public ContentBean get(String id) {
        return contentMapper.query(id);
    }

    @Override
    public List<ContentBean> getList(int offset, int limit, String fid) {
        Criteria criteria = new Criteria();
        criteria.add("fid", fid);
        criteria.setOffset(offset);
        criteria.setLimit(limit);
        criteria.setOrder("sort desc");
        return contentMapper.queryList(criteria);
    }

    @Override
    public void delete(String id) {
        contentMapper.delete(id);
    }
}