package com.lanxin.pandora.service.impl;

import java.util.ArrayList;
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
    public int count(String fid) {
        Criteria criteria = new Criteria();
        criteria.add("fid", fid);
        return contentMapper.count(criteria);
    }

    @Override
    public ContentBean get(String id) {
        return contentMapper.query(id);
    }

    @Override
    public List<String> getBreadcrumb(String id, int level) {
        ArrayList<String> array = new ArrayList<>();
        ContentBean contentBean = contentMapper.query(id);
        if (contentBean != null) {
            array.add(contentBean.getId() + "^"+contentBean.getTitle());
            while (--level > 0) {
                contentBean = contentMapper.query(contentBean.getFid());
                if (contentBean == null) {
                    break;
                }
                array.add(0, contentBean.getId() + "^"+contentBean.getTitle());
            }
        }
        return array;
    }

    @Override
    public List<ContentBean> getSimpleList(int offset, int limit, String fid) {
        Criteria criteria = new Criteria();
        criteria.setSelect("id,fid,cover,title,keyword,data,sort,createTime,changeTime,status");
        criteria.add("fid", fid);
        criteria.setOffset(offset);
        criteria.setLimit(limit);
        criteria.setOrder("sort desc");
        return contentMapper.queryList(criteria);
    }

    @Override
    public void updateInfo(Map<String, Object> data) {
        data.put("changeTime", Calendar.getInstance().getTimeInMillis() / 1000);
        contentMapper.updateInfo(data);
    }

    @Override
    public void delete(String id) {
        contentMapper.delete(id);
    }
}