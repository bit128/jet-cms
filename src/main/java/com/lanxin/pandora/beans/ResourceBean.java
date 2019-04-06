package com.lanxin.pandora.beans;

import java.io.Serializable;

public class ResourceBean implements Serializable {

    public final static long serialVersionUID = 2L;

    private String id;
    private String bid = "";
    private String type = "file";
    private String path = "";
    private String name = "";
    private long size = 0;
    private int time = 0;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return this.bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}