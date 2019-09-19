package com.oliver.lib.network.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.oliver.lib.network.okhttp.listener.DisposeDataHandle;
import com.oliver.lib.network.okhttp.listener.DisposeDataListener;
import com.oliver.lib.network.okhttp.listener.DisposeDownloadListener;

import okhttp3.Callback;

public abstract class OkCommonBaseCallback implements Callback {
    protected final String EMPTY_MSG = "no data";
    protected final String JSON_MSG = "json 解析错误";
    protected final int NETWORK_ERROR = -1; // 网络异常
    protected final int JSON_ERROR = -2; // JSON解析错误
    protected final int OTHER_ERROR = -3; // 未知异常
    protected final int IO_ERROR = -4; //文件IO异常
    protected Handler mDeliveryHandler; //转发到UI线程
    protected DisposeDataListener mListener; //数据回调
    protected DisposeDownloadListener mDownloadListener; //文件下载回调
    protected Class<?> mClass; // json解析对象
    protected String mPath; //文件下载保存路径

    protected OkCommonBaseCallback(DisposeDataHandle dataHandle){
        mDeliveryHandler = new Handler(Looper.getMainLooper());
        if(dataHandle.mListener instanceof DisposeDataListener){
            mListener = (DisposeDataListener) dataHandle.mListener;
        }else if(dataHandle.mListener instanceof DisposeDownloadListener){
            mDownloadListener = (DisposeDownloadListener) dataHandle.mListener;
        }
        mClass = dataHandle.mClass;
        mPath = dataHandle.mSource;
    }

}
