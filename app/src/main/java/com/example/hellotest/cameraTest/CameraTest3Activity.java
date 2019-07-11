package com.example.hellotest.cameraTest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Picture;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.hellotest.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraTest3Activity extends AppCompatActivity {
    CameraPreview cameraPreview ;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test3);

        checkCameraHardware(this);

        //((CameraPreview) findViewById(R.id.camera_preview)).init(initCamera());
        //((CameraPreview) findViewById(R.id.camera_preview)).init(Camera.open());
        camera = Camera.open();

        CameraPreview cameraPreview = new CameraPreview(this);
        cameraPreview.init(camera);
        FrameLayout frameLayout = findViewById(R.id.camera_preview_view);
        frameLayout.addView(cameraPreview);

        Button captureBtn = findViewById(R.id.btn_capture);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean b, Camera c) {
                        camera.takePicture(null, null, mPicture);
                    }
                });
            }
        });
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera c) {
            File pictureFile = new File("/sdcard/" + System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(bytes);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            camera.startPreview();
        }
    };

    private void checkCameraHardware(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Log.e("zdf", "has camera");
        }else {
            Log.e("zdf", "has no camera");
        }

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            Log.e("zdf", "has one camera");
        }else {
            Log.e("zdf", "has no any camera");
        }

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS)){
            Log.e("zdf", "camera can autofocus");
        }else {
            Log.e("zdf", "camera cannot autofocus");
        }

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Log.e("zdf", "camera can flash");
        }else {
            Log.e("zdf", "camera cannot flash");
        }

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)){
            Log.e("zdf", " front camera");
        }else {
            Log.e("zdf", "no front camera");
        }

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_LEVEL_FULL)){
            Log.e("zdf", " camera level full");
        }else {
            Log.e("zdf", "camera no level  full");
        }
    }

    //初始化相机
    public Camera initCamera(){
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();

        for(int camIdx  = 0; camIdx < cameraCount; camIdx++){
            Camera.getCameraInfo(camIdx, cameraInfo);
            //在这里打开的是前置，可选择打开后置
            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                cam = Camera.open(camIdx);
            }
        }
        return cam;
    };

    @Override
    protected void onDestroy() {
        if(camera != null){
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        super.onDestroy();
    }
}
