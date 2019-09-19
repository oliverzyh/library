package com.oliver.lib.network.okhttp.listener;

/**
 * @function 监听下载进度
 */
public interface DisposeDownloadListener extends DisposeBaseListener {
    void onProgress(int progress);
}