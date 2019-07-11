package com.example.hellotest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.hellotest.common.Constants;
import com.example.hellotest.common.DownloadCallback;
import com.example.hellotest.common.HttpUtil;

public class MvcActivity extends AppCompatActivity {

    private Context meContext;
    private ImageView mImageView;
    private MyHandler mMyHandler;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        meContext = this;
        init();
    }

    private void init(){
        mImageView = (ImageView) findViewById(R.id.image);
        mMyHandler = new MyHandler();

        progressDialog = new ProgressDialog(meContext);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("下载文件");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        findViewById(R.id.success).setOnClickListener(view -> {
            progressDialog.show();
            Log.d("zdf", Constants.DOWNLOAD_URL);
            Log.d("zdf", Constants.LOCAL_FILE_PATH);
            HttpUtil.HttpGet(Constants.DOWNLOAD_URL, new DownloadCallback(mMyHandler));
        });

        findViewById(R.id.fail).setOnClickListener(view -> {
            progressDialog.show();
            HttpUtil.HttpGet(Constants.DOWNLOAD_ERROR_URL, new DownloadCallback(mMyHandler));
        });
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 300:
                    int percent = msg.arg1;
                    if(percent < 100){
                        progressDialog.setProgress(percent);
                    }else {
                        progressDialog.dismiss();
                        Glide.with(meContext).load(Constants.LOCAL_FILE_PATH).into(mImageView);
                    }
                    break;
                case 404:
                    progressDialog.dismiss();
                    Toast.makeText(meContext, "Download fail!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}


