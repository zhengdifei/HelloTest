package com.example.hellotest.mvp;

public class DownloadPresenter implements IDownloadPresenter {
    private IDownloadModel iDownloadModel;
    private IDownloadView iDownloadView;

    public DownloadPresenter(IDownloadView iDownloadView){
        this.iDownloadView = iDownloadView;
        this.iDownloadModel = new DownloadModel(this);
    }

    @Override
    public void download(String url) {
        iDownloadView.showProgressBar(true);
        iDownloadModel.download(url);
    }

    @Override
    public void downloadSuccess(String result) {
        iDownloadView.showProgressBar(false);
        iDownloadView.setView(result);
    }

    @Override
    public void downloadProgress(int progress) {
        iDownloadView.showProcessProgress(progress);
    }

    @Override
    public void downloadFail() {
        iDownloadView.showProgressBar(false);
        iDownloadView.showFailToast();
    }
}
