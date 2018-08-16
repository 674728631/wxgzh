package com.liuzhen.wxgzh.service.impl;

import com.liuzhen.wxgzh.dao.inter.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenImpl implements TokenMapper {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Map<String, Object> getTokenTime(Map<String, Object> inParam) {
        return tokenMapper.getTokenTime(inParam);
    }
}
