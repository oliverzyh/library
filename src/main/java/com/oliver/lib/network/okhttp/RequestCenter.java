package com.oliver.lib.network.okhttp;

import com.oliver.lib.network.okhttp.listener.DisposeDataHandle;
import com.oliver.lib.network.okhttp.listener.DisposeDataListener;
import com.oliver.lib.network.okhttp.listener.DisposeDownloadListener;
import com.oliver.lib.network.okhttp.request.OkCommonRequest;
import com.oliver.lib.network.okhttp.request.OkRequestParams;

public class RequestCenter {

    public static void postRequest(String url, OkRequestParams params, OkRequestParams header, DisposeDataListener listener,Class clazz){
        CommonOkHttpClient.post(OkCommonRequest.createPostRequest(url,params,header),new DisposeDataHandle(listener,clazz));
    }

    public static void postRequest(String url, OkRequestParams params, DisposeDataListener listener,Class clazz){
        CommonOkHttpClient.post(OkCommonRequest.createPostRequest(url,params),new DisposeDataHandle(listener,clazz));
    }
    public static void postRequest(String url, OkRequestParams params, DisposeDataListener listener){
        CommonOkHttpClient.post(OkCommonRequest.createPostRequest(url,params),new DisposeDataHandle(listener));
    }
    public static void getRequest(String url, OkRequestParams params, OkRequestParams header, DisposeDataListener listener,Class clazz){
        CommonOkHttpClient.get(OkCommonRequest.createGetRequest(url,params,header),new DisposeDataHandle(listener,clazz));
    }


    public static void getRequest(String url, OkRequestParams params, DisposeDataListener listener,Class clazz){
        CommonOkHttpClient.post(OkCommonRequest.createGetRequest(url,params),new DisposeDataHandle(listener,clazz));
    }
    public static void getRequest(String url, OkRequestParams params, DisposeDataListener listener){
        CommonOkHttpClient.post(OkCommonRequest.createGetRequest(url,params),new DisposeDataHandle(listener));
    }

    /**
     * 文件上传
     * @param url
     * @param params
     * @param listener
     */
    public static void uploadFile(String url,OkRequestParams params,DisposeDataListener listener){

        CommonOkHttpClient.post(OkCommonRequest.createMultiPostRequest(url,params),new DisposeDataHandle(listener));
    }

    /**
     * 文件下载
     * @param url
     * @param filePath
     * @param listener
     */
    public static void downloadFile(String url,String filePath, DisposeDownloadListener listener){
        CommonOkHttpClient.downloadFile(OkCommonRequest.createDownloadRequest(url),new DisposeDataHandle(listener,filePath));
    }

}
