package com.liuzhen.wxgzh.dao.inter;

import com.liuzhen.wxgzh.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    @ResultMap("MessageMap")
    @Select("select * from message")
    public List<Message> findAll();

    public List<Map<String,Object>> getAll();

    public List<Map<String,Object>> getByKey(Map<String,Object> inParam);
}
