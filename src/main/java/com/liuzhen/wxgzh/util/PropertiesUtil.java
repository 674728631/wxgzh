package com.liuzhen.wxgzh.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    /**
     * 根据文件内容返回string
     * @param fileName
     * @return
     */
    public static String getFileJsonToString(String fileName){
        File file = new File(fileName);
        StringBuffer sb = new StringBuffer();
        BufferedReader bfr = null;
        try{
            bfr = new BufferedReader(new FileReader(file));
            String s;
            while((s=bfr.readLine()) != null){
                sb.append(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bfr!=null){
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据文件内容返回json对象
     * @param fileName
     * @return
     */
    public static JSONObject getFileJsonToJson(String fileName){
        File file = new File(fileName);
        StringBuffer sb = new StringBuffer();
        BufferedReader bfr = null;
        try{
            bfr = new BufferedReader(new FileReader(file));
            String s;
            while((s=bfr.readLine()) != null){
                sb.append(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bfr!=null){
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSON.parseObject(sb.toString());
    }

    /**
     * 根据路径返回properties
     * @param fileName
     * @return
     */
    public static Properties getProperties(String fileName) {
        Properties prop = new Properties();

        InputStream in = PropertiesUtil.class.getResourceAsStream("/" + fileName);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * 根据路径和key，获取value
     * @param fileName
     * @param key
     * @return
     */
    public static String getPropertiesValueByKey(String fileName, String key) {
        Properties prop = new Properties();

        InputStream in = PropertiesUtil.class.getResourceAsStream("/" + fileName);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String value = prop.getProperty(key);
        return value;

    }

    public static void main(String[] args) {
        String str =  getFileJsonToString("E:\\Java_Projects\\wxgzh\\src\\main\\resources\\menu.json");
        System.out.println(str);
    }

}
