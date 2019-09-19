package com.oliver.lib.network.okhttp.request;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求参数
 */
public class OkRequestParams {

    public ConcurrentHashMap<String,String> urlParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String,Object> fileParams = new ConcurrentHashMap<>();

    public void put(String key ,String value){
        if(key != null && value != null){
            urlParams.put(key,value);
        }
    }

    public void put(String key ,Object object){
        if(key != null){
            fileParams.put(key,object);
        }
    }
}
