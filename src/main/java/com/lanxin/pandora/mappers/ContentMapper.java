package com.lanxin.pandora.mappers;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.tools.Criteria;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {
    public Map<String, Integer> findNewSort(String fid);
    public void insert(ContentBean content);
    public ContentBean query(String id);
    public List<ContentBean> queryList(Criteria criteria);
}