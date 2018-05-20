package com.alankzh.util;

import com.alankzh.annotation.Immutable;

@Immutable
public class JsonUtil {
    /**
     * 返回包含resultCode的json字符串
     * @param resultCode
     * @return
     */
    public static String commonResultCodeJson(int resultCode) {
        String json=null;
        if(resultCode==0) {
            json="{\"resultCode\":\""+resultCode+"\",\"message\":\"成功\"}";
        }else if(resultCode==1) {
            json="{\"resultCode\":\""+resultCode+"\",\"message\":\"失败\"}";            
        }
        return json;
    }
    
    public static String commonResultCodeJson(int resultCode,String message) {
        String json=null;
        if(resultCode==0) {
            json="{\"resultCode\":\""+resultCode+"\",\"message\":\"成功\"}";
        }else if(resultCode==1) {
            json="{\"resultCode\":\""+resultCode+"\",\"message\":\""+message+"\"}";            
        }
        return json;
    }
}
