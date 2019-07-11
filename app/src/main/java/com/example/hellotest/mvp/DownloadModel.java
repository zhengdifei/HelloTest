package com.example.hellotest.mvp;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hellotest.common.Constants;
import com.example.hellotest.common.DownloadCallback;
import com.example.hellotest.common.HttpUtil;

public class DownloadModel implements IDownloadModel {
    private IDownloadPresenter iDownloadPresenter;
    private MyHandler myHandler = new MyHandler();

    public DownloadModel(IDownloadPresenter iDownloadPresenter){
        this.iDownloadPresenter = iDownloadPresenter;
    }
    @Override
    public void download(String url) {
        HttpUtil.HttpGet(url, new DownloadCallback(myHandler));
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 300:
                    int percent = msg.arg1;
                    if(percent < 100){
                        iDownloadPresenter.downloadProgress(percent);
                    }else {
                       iDownloadPresenter.downloadSuccess(Constants.LOCAL_FILE_PATH);
                    }
                    break;
                case 404:
                    iDownloadPresenter.downloadFail();
                    break;
                default:
                    break;
            }
        }
    }
}
