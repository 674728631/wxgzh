package com.liuzhen.wxgzh.util;

import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送模板消息工具
 */
public class TemplateUtil {

    // 发送模板消息接口
    private static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    // 模板id
    private static final String TEMP_ID = "H_48yyVPKflcGmPpLznJMxeJEGaehzsHxThSTyWa900";


    public static void sendTemplate(){
        // 获取模板消息
        JSONObject dataJson = PropertiesUtil.getFileJsonToJson("E:\\Java_Projects\\wxgzh\\src\\main\\resources\\moban.json");
        // 用户id
//        dataJson.put("touser","okjrP1HDTm6dU_DBWXT1iJcgLHtw");
        dataJson.put("touser","okjrP1AtmVKpxHKgbIfbLGkdxTiY");
        // 消息id
        dataJson.put("template_id",TEMP_ID);
        //
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        dataJson.getJSONObject("data").getJSONObject("time").put("value",df.format(new Date()));

        String token = "12_Svwi2WDKEDDHV9VxUncnnAQYcQSCbj5v9WzIm4bnKtjsqB0oEGyj49ijtMgkba90nEGu5IoIsbc8ik8f9oCGzC3ZN60r-B4PlTBrZukeXmk-EadlGKRMGY9kaFo2qB2mF0ioEPZOSWbVrOFaNQReAIAILM";
        String result =  HttpUtil.doPostJson(SEND_TEMPLATE_URL.replace("ACCESS_TOKEN",token),dataJson.toString());
        System.out.println(result);
    }

    public static void main(String[] args) {
        sendTemplate();
    }

}
