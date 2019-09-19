package com.oliver.lib.network.okhttp.request;


import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkCommonRequest {


    public static Request createPostRequest(String url, OkRequestParams params){
        return createPostRequest(url,params,null);
    }

    /**
     * 创建POST Request
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static Request createPostRequest(String url, OkRequestParams params, OkRequestParams header){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if(params != null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()) {
                //添加参数
                formBodyBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        if(header != null){
            for (Map.Entry<String,String> entry:header.urlParams.entrySet()) {
                //添加请求头
                headerBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .headers(headerBuilder.build())
                .post(formBodyBuilder.build())
                .build();
        return request;
    }



    public static Request createGetRequest(String url, OkRequestParams params){
        return createGetRequest(url,params,null);
    }

    /**
     * 创建GET Request
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static Request createGetRequest(String url, OkRequestParams params, OkRequestParams header){
        StringBuilder  urlStringBuilder = new StringBuilder(url).append("?");
        if(params != null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()) {
                //添加参数
                urlStringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        if(header != null){
            for (Map.Entry<String,String> entry:header.urlParams.entrySet()) {
                //添加请求头
                headerBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(urlStringBuilder.toString())
                .headers(headerBuilder.build())
                .get()
                .build();
        return request;
    }

    /**
     * 文件上传
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");
    public static Request createMultiPostRequest(String url, OkRequestParams params){
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);

        if(params != null){
            for (Map.Entry<String,Object> entry : params.fileParams.entrySet()) {
                if(entry.getValue() instanceof File){
                    multipartBody.addFormDataPart(entry.getKey(),entry.getKey(), RequestBody.create( (File) entry.getValue(),FILE_TYPE));
//                    multipartBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create( (File) entry.getValue(),FILE_TYPE));
                }else if(entry.getValue() instanceof String){
                    multipartBody.addFormDataPart(entry.getKey(),entry.getValue().toString());
//                    multipartBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create((String) entry.getValue(),null));
                }
            }
        }
        return new Request.Builder().url(url).post(multipartBody.build()).build();
    }


    /**
     * 文件下载
     * @param url 下载路径
     * @return
     */
    public static Request createDownloadRequest(String url){

        Request request=new Request.Builder().url(url).build();
        return request;
    }
}
