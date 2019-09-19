package com.oliver.lib.network.okhttp.response;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.oliver.lib.network.okhttp.exception.OkHttpException;
import com.oliver.lib.network.okhttp.listener.DisposeDataHandle;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;

import okhttp3.Response;

public class OkCommonJsonCallback<T> extends OkCommonBaseCallback {

    public OkCommonJsonCallback(DisposeDataHandle dataHandle) {
        super(dataHandle);
    }

    @Override
    public void onFailure(@NotNull final Call call, @NotNull final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e)); //网络异常、服务器异常、接口请求失败
            }
        });
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(String result) {
        if(TextUtils.isEmpty(result)){
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        if(mClass == null){
            mListener.onSuccess(result);
        }else{
            try {
                T data = (T) new Gson().fromJson(result,mClass);
                if(data != null){
                    mListener.onSuccess(data);
                }else{
                    mListener.onFailure(new OkHttpException(JSON_ERROR, JSON_MSG));
                    return ;
                }
            }catch(Exception e){
                mListener.onFailure(new OkHttpException(OTHER_ERROR, JSON_MSG));
            }
        }


    }
}
