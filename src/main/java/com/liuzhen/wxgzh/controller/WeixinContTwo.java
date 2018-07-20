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
public class WeixinContTwo {
    private static final Logger logger = LoggerFactory.getLogger(WeixinContTwo.class);
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

    @RequestMapping(value="/weChat",method = RequestMethod.POST,produces= {MediaType.TEXT_XML_VALUE})
    @ResponseBody
    public Object handleMessage(@RequestBody InMessage msg){
        logger.info("in msg==="+ msg);

        String msgType = msg.getMsgType();
        OutMessage out = new OutMessage();
        out.setToUserName(msg.getFromUserName());
        out.setFromUserName(msg.getToUserName());
        out.setCreateTime(new Date().getTime());
        if("text".equals(msgType)){
            out.setMsgType("text");
            String content = msg.getContent();
            if(content.contains("照片")){
                out.setContent("还没有照片");
            }else if(content.contains("姓名")){
                out.setContent("LiuHuanYu\nHaHa");
            }else{
                out.setContent(content);
            }
        }else if("image".equals(msgType)){
            out.setMsgType("image");
            out.setMediaId(new String[]{msg.getMediaId()});
        }else if("event".equals(msgType)){
            if("subscribe".equals(msg.getEvent())){
                out.setMsgType("text");
                out.setContent("欢迎关注！");
            }else if("CLICK".equals(msg.getEvent())){

            }

        }else{
            out.setMsgType("text");
            out.setContent("对不起，暂不支持该功能！");
        }
        return out;
    }
}
