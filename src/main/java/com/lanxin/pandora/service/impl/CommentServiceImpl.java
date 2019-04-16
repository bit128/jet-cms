package com.lanxin.pandora.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.lanxin.pandora.beans.CommentBean;
import com.lanxin.pandora.mappers.CommentMapper;
import com.lanxin.pandora.service.CommentService;
import com.lanxin.pandora.tools.Criteria;
import com.lanxin.pandora.tools.DateTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public String insert(String content, String bid, String uid) {
        CommentBean comment = new CommentBean();
        comment.setId(DateTools.uniqid());
        comment.setBid(bid);
        comment.setUid(uid);
        comment.setContent(content);
        comment.setCreateTime(Calendar.getInstance().getTimeInMillis()/1000);
        comment.setStatus(STATUS_HIDE);
        commentMapper.insert(comment);
        return comment.getId();
    }

    @Override
    public int count(String bid, String status, String keyword) {
        Criteria criteria = new Criteria();
        criteria.addNotEmpty("bid", bid);
        criteria.addNotEmpty("status", status);
        criteria.addNotEmpty("keyword", keyword);
        return commentMapper.count(criteria);
    }

    @Override
    public CommentBean get(String id) {
        return commentMapper.query(id);
    }

    @Override
    public List<CommentBean> getList(int offset, int limit, String bid, String status, String keyword) {
        Criteria criteria = new Criteria();
        criteria.addNotEmpty("bid", bid);
        criteria.addNotEmpty("status", status);
        criteria.addNotEmpty("keyword", keyword);
        criteria.setOffset(offset);
        criteria.setLimit(limit);
        criteria.setOrder("createTime desc");
        return commentMapper.queryList(criteria);
    }

    @Override
    public void updateInfo(Map<String, Object> data) {
        commentMapper.updateInfo(data);
    }

    @Override
    public void delete(String id) {
        commentMapper.delete(id);
    }

}