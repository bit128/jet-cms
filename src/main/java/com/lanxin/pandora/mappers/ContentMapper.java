package com.lanxin.pandora.mappers;

import java.util.Map;

import com.lanxin.pandora.beans.ContentBean;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {
    public Map<String, Integer> findNewSort(String fid);
    public void addContent(ContentBean content);
    public ContentBean findContentById(String id);
}