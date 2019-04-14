package com.lanxin.pandora.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.UserBean;
import com.lanxin.pandora.beans.UserTokenBean;
import com.lanxin.pandora.mappers.UserMapper;
import com.lanxin.pandora.service.UserService;
import com.lanxin.pandora.tools.Criteria;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    public final static int STATUS_LOCK     = 0; //冻结
    public final static int STATUS_NORMAL   = 1; //普通用户
    public final static int STATUS_VIP      = 2; //会员
    public final static int STATUS_ADMIN    = 9; //管理员

    public final static int ROLE_CONTENT    = 1;
    public final static int ROLE_RESOURCE   = 2;
    public final static int ROLE_USER       = 4;
    public final static int ROLE_LOG        = 8;
    public final static int ROLE_COG        = 16;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean existAccount(String account) {
        Criteria criteria = new Criteria();
        criteria.add("account", account);
        if (userMapper.count(criteria) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkToken(UserTokenBean userToken) {
        if (!userToken.getToken().isEmpty()) {
            UserBean user = userMapper.query(userToken.getUid());
            if (user != null) {
                if (user.getStatus() > STATUS_LOCK && user.getToken().equals(userToken.getToken())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 构建用户令牌
     * @param uid   用户id
     * @return
     */
    private UserTokenBean buildToken(String uid, String ip, int count) {
        String token = DigestUtils.md5DigestAsHex(DateTools.uniqid().getBytes());
        long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", uid);
        data.put("token", token);
        data.put("ip", ip);
        data.put("count", count + 1);
        data.put("activeTime", timestamp);
        userMapper.updateInfo(data);
        return new UserTokenBean(uid, token, timestamp);
    }

    @Override
    public UserTokenBean login(String account, String password, String ip) {
        UserBean user = userMapper.queryByAccount(account);
        if (user != null) {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (password.equals(user.getPassword())) {
                if (user.getStatus() > STATUS_LOCK) {
                    return buildToken(user.getId(), ip, user.getCount());
                }
            }
        }
        return null;
    }

    @Override
    public void logout(String id) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("token", "");
        userMapper.updateInfo(data);
    }
    
    @Override
    public UserTokenBean insert(String account, String password, String name, String ip){
        password = DigestUtils.md5DigestAsHex(password.trim().getBytes());
        UserBean user = new UserBean();
        user.setId(DateTools.uniqid());
        user.setAccount(account);
        user.setPassword(password);
        user.setName(name);
        user.setIp(ip);
        user.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
        user.setStatus(STATUS_NORMAL);
        userMapper.insert(user);
        return buildToken(user.getId(), ip, 0);
    }

    @Override
    public int count(String status, String keyword) {
        Criteria criteria = new Criteria();
        criteria.add("status", status);
        if(!keyword.isEmpty()) {
            criteria.add("account", "%"+keyword+"%", "like", "AND");
        }
        return userMapper.count(criteria);
    }

    @Override
    public UserBean get(String id) {
        return userMapper.query(id);
    }

    @Override
    public List<UserBean> getList(int offset, int limit, String status, String keyword) {
        Criteria criteria = new Criteria();
        criteria.setOffset(offset);
        criteria.setLimit(limit);
        criteria.add("status", status);
        if(!keyword.isEmpty()) {
            criteria.add("account", "%"+keyword+"%", "like", "AND");
        }
        criteria.setOrder("createTime desc");
        return userMapper.queryList(criteria);
    }

    @Override
    public void updateInfo(Map<String, Object> data) {
        userMapper.updateInfo(data);
    }

    @Override
    public void delete(String id) {
        userMapper.delete(id);
    }

}