package com.lanxin.pandora.service;

import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.UserBean;
import com.lanxin.pandora.beans.UserTokenBean;

public interface UserService {

    public final static int STATUS_LOCK     = 0; //冻结
    public final static int STATUS_NORMAL   = 1; //普通用户
    public final static int STATUS_VIP      = 2; //会员
    public final static int STATUS_ADMIN    = 9; //管理员

    public final static int ROLE_CONTENT    = 1;
    public final static int ROLE_RESOURCE   = 2;
    public final static int ROLE_USER       = 4;
    public final static int ROLE_LOG        = 8;
    public final static int ROLE_COG        = 16;
    
    public UserTokenBean insert(String account, String password, String name, String ip);
    public int count(String status, String keyword);
    public UserBean get(String id);
    public List<UserBean> getList(int offset, int limit, String status, String keyword);
    public void updateInfo(Map<String, Object> data);
    public void delete(String id);

    public boolean existAccount(String account);
    public boolean checkToken(UserTokenBean userToken);
    public UserTokenBean login(String account, String password, String ip);
    public void logout(String id);
}