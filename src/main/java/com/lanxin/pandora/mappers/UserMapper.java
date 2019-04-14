package com.lanxin.pandora.mappers;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.UserBean;
import com.lanxin.pandora.tools.Criteria;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public void insert(UserBean user);
    public int count(Criteria criteria);
    public UserBean query(String id);
    public List<UserBean> queryList(Criteria criteria);
    public int updateInfo(@Param("map") Map<String, Object> data);
    public int delete(String id);

    public UserBean queryByAccount(String account);
}