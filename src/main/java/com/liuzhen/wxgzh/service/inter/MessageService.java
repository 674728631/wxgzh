package com.liuzhen.wxgzh.service.inter;

import com.liuzhen.wxgzh.pojo.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    public List<Message> getAllMessage();
    public List<Map<String,Object>> getAll();
    public List<Map<String,Object>> getByKey(Map<String,Object> inParam);
}
