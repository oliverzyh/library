package com.oliver.lib.network.okhttp;

import com.oliver.lib.network.okhttp.cookie.SimpleCookieJar;
import com.oliver.lib.network.okhttp.https.HttpsUtils;
import com.oliver.lib.network.okhttp.listener.DisposeDataHandle;
import com.oliver.lib.network.okhttp.response.OkCommonFileCallback;
import com.oliver.lib.network.okhttp.response.OkCommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class CommonOkHttpClient {

    private static final int TIME_OUT = 2000;
    private static OkHttpClient mOkHttpClient;
    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });

//        /**
//         *  为所有请求添加请求头，看个人需求
//         */
//        okHttpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request =
//                        chain.request().newBuilder().addHeader("User-Agent", "Imooc-Mobile") // 标明发送本次请求的客户端
//                                .build();
//                return chain.proceed(request);
//            }
//        });
        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
        /**
         * https 设置
         */
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okHttpClientBuilder.build();
    }


    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


    /**
     * 通过构造好的Request,Callback去发送请求
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new OkCommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new OkCommonJsonCallback(handle));
        return call;
    }



    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new OkCommonFileCallback(handle));
        return call;
    }



}
