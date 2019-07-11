package com.example.hellotest.mvp;

public interface IDownloadView {
    void showProgressBar(boolean show);

    void showProcessProgress(int progress);

    void setView(String view);

    void showFailToast();
}
