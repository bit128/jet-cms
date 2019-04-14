package com.lanxin.pandora.beans;

import java.io.Serializable;

public class UserTokenBean implements Serializable {

    private static final long serialVersionUID = 4L;

    private String uid = "";
    private String token = "";
    private long timestamp = 0;

    public UserTokenBean(){}
    
    public UserTokenBean(String uid, String token, long timestamp) {
        this.uid = uid;
        this.token = token;
        this.timestamp = timestamp;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}