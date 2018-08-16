package com.liuzhen.wxgzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.liuzhen.wxgzh.pojo.Message;
import com.liuzhen.wxgzh.service.inter.MessageService;
import com.liuzhen.wxgzh.util.UserUtil;
import com.liuzhen.wxgzh.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class DbCon {

    @Autowired
    private MessageService service;

    @RequestMapping("/message")
    @ResponseBody
    public List<Message> getAll(){
        List<Message> list =service.getAllMessage();
        return list;
    }

    @RequestMapping("/message2")
    @ResponseBody
    public List<Map<String,Object>> get(){
        List<Map<String,Object>> list =service.getAll();
        return list;
    }

    @RequestMapping("/message3")
    @ResponseBody
    public List<Map<String,Object>> getByKey(String key){
        Map<String,Object> inParam = new HashMap<>();
//        inParam.put("KEY","%"+key+"%");
        inParam.put("KEY",key);
        List<Map<String,Object>> list =service.getByKey(inParam);
        return list;
    }

    @RequestMapping("/index")
    public String userInfo(Model model,String code){
        System.out.println(code);
        if(null!=code){
            JSONObject json = WeChatUtil.getWebAccessToken(code);

            String token = json.getString("access_token");
            String openid = json.getString("openid");

           JSONObject userInfo = UserUtil.getUserInfo(token,openid);
           Set<Map.Entry<String, Object>> entries = userInfo.entrySet();
            for (Map.Entry<String, Object> entry:entries) {
                model.addAttribute(entry.getKey(),entry.getValue());
            }
        }
        return "/index";
    }
}
