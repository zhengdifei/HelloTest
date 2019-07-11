package com.example.hellotest;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GlideActivity extends AppCompatActivity {
    private String picUrl = "http://img1.3lian.com/img013/v4/96/d/45.jpg";

    private static final int ONE_BTYE = 1024;
    private static final int ONE_MB = 1024 * ONE_BTYE;

    private ActivityManager activityManager;
    private StringBuffer sb = new StringBuffer();
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        sb.append("当前应用可用Heap Size is " + activityManager.getMemoryClass() + "MB\n");
        sb.append("要加载的图片在虚拟机所占的内存：" + 2560 * 1600 *4 / ONE_MB + "MB\n");

        info = findViewById(R.id.info);
        info.setText(sb);

        final ImageView imageView = findViewById(R.id.image);
        Glide.with(this).load(picUrl).into(imageView);
        findViewById(R.id.loadimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.large_image);
                imageView.setImageBitmap(bitmap);
            }
        });
//        try {
//            Runtime.getRuntime().exec("adb logcat");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zdf", "onResume: ");
    }
}
