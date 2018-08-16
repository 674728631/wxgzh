package com.liuzhen.wxgzh.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 跟关注用户相关工具类
 */
public class UserUtil {

    // 获取帐号的关注者列表url
    private static final String GET_OPENID_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    // 普通获取用户信息uri
    private static final String GET_USERINFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // 网页授权获取用户信息uri
    private static final String GET_WEB_USERINFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 获取所有关注用户（一次10000个，需要多次）
     * @return
     */
    public static List<String> getAllUserOpenID(){

        String token = "12_Svwi2WDKEDDHV9VxUncnnAQYcQSCbj5v9WzIm4bnKtjsqB0oEGyj49ijtMgkba90nEGu5IoIsbc8ik8f9oCGzC3ZN60r-B4PlTBrZukeXmk-EadlGKRMGY9kaFo2qB2mF0ioEPZOSWbVrOFaNQReAIAILM";
        String resultStr = HttpUtil.doGet(GET_OPENID_URL.replace("ACCESS_TOKEN",token).replace("NEXT_OPENID",""));
        JSONObject json = JSONObject.parseObject(resultStr);
        List<String> list = json.getJSONObject("data").getObject("openid",List.class);
        String next_openid = json.getJSONObject("data").getString("next_openid");
        return list;
    }

    public static JSONObject getUserInfo(){
        String token = "12_Svwi2WDKEDDHV9VxUncnnAQYcQSCbj5v9WzIm4bnKtjsqB0oEGyj49ijtMgkba90nEGu5IoIsbc8ik8f9oCGzC3ZN60r-B4PlTBrZukeXmk-EadlGKRMGY9kaFo2qB2mF0ioEPZOSWbVrOFaNQReAIAILM";
        String openid="okjrP1AtmVKpxHKgbIfbLGkdxTiY";
        String resultStr = HttpUtil.doGet(GET_USERINFO_URL.replace("ACCESS_TOKEN",token).replace("OPENID",openid));
        JSONObject json = JSONObject.parseObject(resultStr);
        return  json;
    }

    public static JSONObject getUserInfo(String token,String openid){
//        String token = "12_Svwi2WDKEDDHV9VxUncnnAQYcQSCbj5v9WzIm4bnKtjsqB0oEGyj49ijtMgkba90nEGu5IoIsbc8ik8f9oCGzC3ZN60r-B4PlTBrZukeXmk-EadlGKRMGY9kaFo2qB2mF0ioEPZOSWbVrOFaNQReAIAILM";
//        String openid="okjrP1AtmVKpxHKgbIfbLGkdxTiY";
        String resultStr = HttpUtil.doGet(GET_WEB_USERINFO_URL.replace("ACCESS_TOKEN",token).replace("OPENID",openid));
        JSONObject json = JSONObject.parseObject(resultStr);
        return  json;
    }

    public static void main(String[] args) {
//        List<String> list = getAllUserOpenID();
//        System.out.printf(list.toString());

        JSONObject json =  getUserInfo();
        System.out.printf(json.toString());
    }


}
