package com.zzz.sell.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author zzz
 * @description http工具集
 * @date 2020/2/28
 */
public class HttpUtil {
    public static String getJson(String key,String value){
        return "{\'" + key +"\':" + "\'" + value + "\'}";
    }
    public static String getJson(HashMap<String, Object> map){
       return JSONObject.toJSONString(map);
    }
}
