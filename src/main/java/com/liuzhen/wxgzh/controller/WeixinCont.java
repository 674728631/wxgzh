package com.liuzhen.wxgzh.controller;

import com.liuzhen.wxgzh.pojo.*;
import com.liuzhen.wxgzh.util.SecurityUtil;
import com.liuzhen.wxgzh.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping(value="/weChat1",method = RequestMethod.GET)
public class WeixinCont {
    private static final Logger logger = LoggerFactory.getLogger(WeixinCont.class);
    @RequestMapping(value="/weChat",method = RequestMethod.GET)
    @ResponseBody
    public String validate(String signature,String timestamp,String nonce,String echostr){
        // 1.将入参排序
        logger.info("wecaht===========================");
        try{
            String[] arr = {timestamp, nonce, WeChatUtil.TOKEN};
            logger.info("wecaht="+timestamp+","+nonce+","+WeChatUtil.TOKEN+","+echostr);
            Arrays.sort(arr);
            // 将入参拼装准备加密
            StringBuilder sb = new StringBuilder();
            for (String s:arr) {
                sb.append(s);
            }
            logger.info("wecaht="+sb);
            // 将加密后的字符串进行比较，判断是否来自微信
            logger.info("wecaht="+SecurityUtil.sha1(sb.toString())+","+signature);
            if(SecurityUtil.sha1(sb.toString()).equals(signature)){
                return  echostr;
            }
        }catch (Exception e){
            return "false";
        }

        return "false";

    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        // test url
        logger.info("test===========================");
        return "welcome! test successed!";
    }

    @RequestMapping(value="/weChat",method = RequestMethod.POST,produces= {MediaType.TEXT_XML_VALUE})
    @ResponseBody
    public Object handleMessage(@RequestBody InMessage msg){
        logger.info("in msg==="+ msg);

        String msgType = msg.getMsgType();
        if("text".equals(msgType)){
            TextOutMessage out = new TextOutMessage();
            out.setToUserName(msg.getFromUserName());
            out.setFromUserName(msg.getToUserName());
            out.setMsgType(msg.getMsgType());
            out.setCreateTime(new Date().getTime());
            out.setContent(msg.getContent());
            return out;
        }else if("image".equals(msgType)){
            PicOutMessage out = new PicOutMessage();
            out.setToUserName(msg.getFromUserName());
            out.setFromUserName(msg.getToUserName());
            out.setMsgType(msg.getMsgType());
            out.setCreateTime(new Date().getTime());
            out.setPicUrl(msg.getPicUrl());
            out.setMediaId(new String[]{msg.getMediaId()});
            return out;
        }else if("voice".equals(msgType)){
            VoiceOutMessage out = new VoiceOutMessage();
            out.setToUserName(msg.getFromUserName());
            out.setFromUserName(msg.getToUserName());
            out.setMsgType("voice");
            out.setCreateTime(new Date().getTime());
            out.setFormat(msg.getFormat());
            out.setMediaId(new String[]{msg.getMediaId()});
            return out;
        }else{
            logger.info("不支持的类型"+msgType);
            TextOutMessage out = new TextOutMessage();
            out.setToUserName(msg.getFromUserName());
            out.setFromUserName(msg.getToUserName());
            out.setMsgType("text");
            out.setCreateTime(new Date().getTime());
            out.setContent("对不起，暂不支持该类型消息！");
            return out;
        }
    }
}
