package com.lanxin.pandora.service.impl;

import java.util.Calendar;
import java.util.Map;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.mappers.ContentMapper;
import com.lanxin.pandora.service.ContentService;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public String newContent(String fid, String defaultTitle) {
        int sort = 1;
        Map<String, Integer> map = contentMapper.findNewSort(fid);
        if (map != null){
            sort = map.get("sort") + 1;
        }
        ContentBean content = new ContentBean();
        content.setId(DateTools.uniqid());
        content.setFid(fid);
        content.setTitle(defaultTitle);
        content.setCreateTime(Calendar.getInstance().getTimeInMillis()/1000);
        content.setSort(sort);
        contentMapper.addContent(content);
        return content.getId();
    }

    @Override
    public ContentBean getContent(String id) {
        return contentMapper.findContentById(id);
    }
}