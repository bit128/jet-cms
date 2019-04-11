package com.lanxin.pandora.mappers;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ResourceBean;
import com.lanxin.pandora.tools.Criteria;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResourceMapper {
    public Map<String, Integer> findNewSort(String bid);
    public void insert(ResourceBean resource);
    public int count(Criteria criteria);
    public ResourceBean query(String id);
    public List<ResourceBean> queryList(Criteria criteria);
    public int updateInfo(@Param("map") Map<String, Object> data);
    public int delete(String id);
}