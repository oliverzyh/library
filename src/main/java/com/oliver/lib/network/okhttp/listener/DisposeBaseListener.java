package com.oliver.lib.network.okhttp.listener;

/**
 * 数据处理回调
 */
public interface DisposeBaseListener<T> {
    /**
     * 请求成功回调事件处理
     */
    void onSuccess(T t);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(Object reasonObj);
}
