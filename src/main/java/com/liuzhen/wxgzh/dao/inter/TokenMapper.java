package com.liuzhen.wxgzh.dao.inter;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TokenMapper {

    public Map<String,Object> getTokenTime(Map<String,Object> inParam);
}
