package com.example.hellotest.mvp;

public interface IDownloadPresenter {
    void download(String url);

    void downloadSuccess(String result);

    void downloadProgress(int progress);

    void downloadFail();
}
