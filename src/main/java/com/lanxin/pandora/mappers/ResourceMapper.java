package com.lanxin.pandora.mappers;

import com.lanxin.pandora.beans.ResourceBean;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {
    public void insert(ResourceBean resource);
}