package com.example.hellotest.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hellotest.R;
import com.example.hellotest.common.Constants;

public class MvpActivity extends AppCompatActivity implements IDownloadView{

    private Context context;
    private ImageView imageView;
    private ProgressDialog progressDialog;
    private IDownloadPresenter iDownloadPresenter;

    private static void onClick(View view) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        context = this;
        init();
    }

    public void init(){
        iDownloadPresenter = new DownloadPresenter(this);

        imageView = (ImageView) findViewById(R.id.image);
        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDownloadPresenter.download(Constants.DOWNLOAD_URL);
            }
        });

        findViewById(R.id.fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDownloadPresenter.download(Constants.DOWNLOAD_ERROR_URL);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.dismiss();
            }
        });

        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("download file");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showProcessProgress(int progress) {
        progressDialog.setProgress(progress);
    }

    @Override
    public void setView(String view) {
        Glide.with(this).load(view).into(imageView);
    }

    @Override
    public void showFailToast() {
        Toast.makeText(this, "Download fail!", Toast.LENGTH_SHORT ).show();
    }
}
