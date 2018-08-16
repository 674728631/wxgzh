package com.liuzhen.wxgzh.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuzhen.wxgzh.service.impl.TokenImpl;
import com.liuzhen.wxgzh.service.inter.TokenService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public class WeChatUtil {
    public static final String TOKEN = "weixintest674";

    //appid
    public static final String APPID = "wx257847967a8a66e3";
    //secret
    public static final String SECRET = "9f379a7ddd9b87345b1a1becbbbe416d";
    //创建菜单接口地址
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //获取access_token的接口地址
    public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static final String GET_WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //缓存的access_token
    private static String accessToken;
    //access_token的失效时间
    private static long expiresTime;

    /**
     * 获取 基础 accessToken
     * @return
     */
    public static String getAccessToken(){
        // 先从数据库取（后期考虑存到缓存）


        //判断accessToken是否已经过期，如果过期需要重新获取
        if(accessToken==null||expiresTime < new Date().getTime()){
            //发起请求获取accessToken
            String result = HttpUtil.doGet(GET_ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", SECRET));
            //把json字符串转换为json对象
            JSONObject json = JSON.parseObject(result);
            //缓存accessToken
//            accessToken = json.getString("access_token");
            accessToken = "11_V3dtS-3NFsWZmGcje4qG5Cdjz3fGzp_IkAymj06ZNRAI9tgKiRybzzPGGUY-dPrfWmcd7SBtRzch-FlR11ryo5dPD7miNU4wTZtml0ulpcChp4rmOIU-n0fXAb9KOeenvfc6Sw5A_Q54vU7OCTKfAIAHYP";
            //11_V3dtS-3NFsWZmGcje4qG5Cdjz3fGzp_IkAymj06ZNRAI9tgKiRybzzPGGUY-dPrfWmcd7SBtRzch-FlR11ryo5dPD7miNU4wTZtml0ulpcChp4rmOIU-n0fXAb9KOeenvfc6Sw5A_Q54vU7OCTKfAIAHYP

            //设置accessToken的失效时间
            long expires_in = json.getLong("expires_in");
            //失效时间 = 当前时间 + 有效期(提前一分钟)
            expiresTime = new Date().getTime()+ (expires_in-60) * 1000;
        }
        return accessToken;
    }

    /**
     * 获取网页授权accessToken
     * @param code
     * @return
     */
    public static JSONObject getWebAccessToken(String code) {
        // 先从数据库取（后期考虑存到缓存）

        String result = HttpUtil.doGet(GET_WEB_ACCESSTOKEN_URL.replace("APPID", APPID).replace("SECRET", SECRET).replace("CODE", code));
        //把json字符串转换为json对象
        JSONObject json = JSON.parseObject(result);

        return json;
    }



    public static void createMenu(String menu){
        String  accessToken = "11_V3dtS-3NFsWZmGcje4qG5Cdjz3fGzp_IkAymj06ZNRAI9tgKiRybzzPGGUY-dPrfWmcd7SBtRzch-FlR11ryo5dPD7miNU4wTZtml0ulpcChp4rmOIU-n0fXAb9KOeenvfc6Sw5A_Q54vU7OCTKfAIAHYP";

        String result = HttpUtil.doPostJson(CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken),menu);
        System.out.println(result);
    }

    public static void main(String[] args) {
//        String menu = PropertiesUtil.getFileJsonToString("E:\\Java_Projects\\wxgzh\\src\\main\\resources\\menu.json");
//        createMenu(menu);

//        uploadMedia();
        getAccessToken();
    }

    public static void uploadMedia(){
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        String token = "12_kOHZ28mSPTTTQ7A5u4zwuavipKU8xmGZ0LLbAqIVSRqifQCt5sYqdxpByHfKJ86UiL8yc0mfm6RTh9O4S4jWacNF23Ywr0KmEnDFwwh63nS7N75DauCaO0my8XHtSQuUmh3HglvdnjChmQgSQACfACAYIK";

        try {
           JSONObject json =  HttpUtil.uploadMedia(url.replace("ACCESS_TOKEN",token).replace("TYPE","image"),"C:\\Users\\admin\\Desktop\\1.jpg");
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
