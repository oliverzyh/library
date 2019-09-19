package com.oliver.lib.network.okhttp.listener;

public class DisposeDataHandle {

    public DisposeBaseListener mListener = null; //回调
    public Class<?> mClass = null; //解析对象
    public String mSource = null; //文件下载地址

    public DisposeDataHandle(DisposeBaseListener listener)
    {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeBaseListener listener, Class<?> clazz)
    {
        this.mListener = listener;
        this.mClass = clazz;
    }

    public DisposeDataHandle(DisposeBaseListener listener, String source)
    {
        this.mListener = listener;
        this.mSource = source;
    }
}
