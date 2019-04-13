package com.lanxin.pandora.service;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.UserBean;
import com.lanxin.pandora.beans.UserTokenBean;

public interface UserService {
    public UserTokenBean insert(String account, String password, String name, String ip);
    public int count(String status, String keyword);
    public UserBean get(String id);
    public List<UserBean> getList(int offset, int limit, String status, String keyword);
    public void updateInfo(Map<String, Object> data);
    public void delete(String id);

    public boolean existAccount(String account);
    public boolean checkToken(String uid, String token);
}