package com.lanxin.pandora.mappers;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.ResourceBean;
import com.lanxin.pandora.tools.Criteria;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {
    public Map<String, Integer> findNewSort(String bid);
    public void insert(ResourceBean resource);
    public List<ResourceBean> queryList(Criteria criteria);
}