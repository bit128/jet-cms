package com.lanxin.pandora.mappers;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ContentBean;
import com.lanxin.pandora.tools.Criteria;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentMapper {
    public Map<String, Integer> findNewSort(String fid);
    public void insert(ContentBean content);
    public int count(Criteria criteria);
    public ContentBean query(String id);
    public List<ContentBean> queryList(Criteria criteria);
    public int updateInfo(@Param("map") Map<String, Object> data);
    public int delete(String id);
}