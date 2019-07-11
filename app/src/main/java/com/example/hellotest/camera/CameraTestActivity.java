package com.example.hellotest.camera;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

import com.example.hellotest.R;

public class CameraTestActivity extends AppCompatActivity {

    Context context;
    SurfaceView surfaceView;
    CameraSurfaceHolder cameraSurfaceHolder = new CameraSurfaceHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        context = this;
        surfaceView = findViewById(R.id.surfaceView1);
        cameraSurfaceHolder.setCameraSurfaceHolder(context, surfaceView);
    }
}
