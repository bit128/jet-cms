package com.lanxin.pandora.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
    public final static int ROLE_COMMENT    = 8;
    
    public UserTokenBean insert(String account, String password, String name, String ip);
    public int count(String status, String keyword);
    public UserBean get(String id);
    public List<UserBean> getList(int offset, int limit, String status, String keyword);
    public void updateInfo(Map<String, Object> data);
    public void delete(String id);

    /**
     * 检测账户是否存在
     * @param account
     * @return
     */
    public boolean existAccount(String account);

    /**
     * 验证用户令牌
     * @param userToken
     * @return
     */
    public boolean checkToken(UserTokenBean userToken);

    /**
     * 验证用户权限
     * @param session
     * @return
     */
    public boolean checkRole(HttpSession session, int role);

    /**
     * 用户登录
     * @param account
     * @param password
     * @param ip
     * @return
     */
    public UserTokenBean login(String account, String password, String ip);

    /**
     * 用户登出
     * @param id
     */
    public void logout(String id);
}