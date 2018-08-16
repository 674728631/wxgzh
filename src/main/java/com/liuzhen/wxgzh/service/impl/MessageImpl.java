package com.liuzhen.wxgzh.service.impl;

import com.liuzhen.wxgzh.dao.inter.MessageMapper;
import com.liuzhen.wxgzh.pojo.Message;
import com.liuzhen.wxgzh.service.inter.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageImpl.class);

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getAllMessage() {

        List<Message> ms = messageMapper.findAll();
        return ms;
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return messageMapper.getAll();
    }

    @Override
    public List<Map<String, Object>> getByKey(Map<String, Object> inParam) {
        logger.info("getByKey 》》"+inParam);
        return messageMapper.getByKey(inParam);
    }
}
