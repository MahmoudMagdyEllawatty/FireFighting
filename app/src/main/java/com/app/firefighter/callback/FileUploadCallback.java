package com.app.firefighter.callback;

public interface FileUploadCallback {
    void onSuccess(String url);
    void onFail(String msg);
}
