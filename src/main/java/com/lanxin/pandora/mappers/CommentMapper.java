package com.lanxin.pandora.mappers;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.CommentBean;
import com.lanxin.pandora.tools.Criteria;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    public void insert(CommentBean comment);
    public int count(Criteria criteria);
    public CommentBean query(String id);
    public List<CommentBean> queryList(Criteria criteria);
    public int updateInfo(@Param("map") Map<String, Object> data);
    public int delete(String id);
}