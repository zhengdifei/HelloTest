package com.example.hellotest.cameraTest;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hellotest.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraTestActivity extends AppCompatActivity {

    private Camera camera;
    private SurfaceView surfaceView;
    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test2);

        ButterKnife.bind(this);

        int numberOfCameras = Camera.getNumberOfCameras();
        Log.e("zdf", "camera number:"  + numberOfCameras);
        int faceBackCameraId = 0 , faceFrontCameraId = 0;
        int faceBackCameraOrientation = 0, faceFrontCameraOrientation = 0;

        for(int i = 0; i < numberOfCameras; ++i){
            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

            Camera.getCameraInfo(i, cameraInfo);

            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK){
                faceBackCameraId = i;
                faceBackCameraOrientation = cameraInfo.orientation;
            }

            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                faceFrontCameraId = i;
                faceFrontCameraOrientation = cameraInfo.orientation;
            }
        }

        surfaceView = findViewById(R.id.surfaceView2);
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        lp.width = 600;
        lp.height =600;
        surfaceView.setLayoutParams(lp);
    }

    @OnClick({R.id.startPreview, R.id.stopPreview, R.id.takePhoto, R.id.startRecorder, R.id.stopRecorder})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.startPreview:
                startPreview();
                break;
            case R.id.stopPreview:
                stopPreview();
                break;
            case R.id.takePhoto:
                takePhoto();
                break;
            case R.id.startRecorder:
                prepareMediaRecorder();
                break;
            case R.id.stopRecorder:
                stopRecorder();
                break;
            default:
                break;
        }
    }

    private void startPreview(){
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Log.e("zdf", "surface created");
                camera = Camera.open(1);
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("zdf", camera.getParameters().toString());
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                Log.e("zdf", "surface changed");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.e("zdf", "surface destoryed");
            }
        });
    }
//    private void startPreview(SurfaceHolder surfaceHolder){
//        final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//        Camera.getCameraInfo(0, cameraInfo);
//        int cameraRotationOffset = cameraInfo.orientation;
//
//        final Camera.Parameters parameters = camera.getParameters();
//
//        camera.setParameters(parameters);
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        camera.startPreview();
//    }

    private void stopPreview(){
        camera.stopPreview();
    }

    private void takePhoto(){
        camera.takePicture(null, null, new Camera.PictureCallback(){

            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                final File pictureFile = new File("/sdcard/" + System.currentTimeMillis() + ".jpg");
                if(pictureFile == null){
                    Log.d("zdf", "Error creating media file");
                    return;
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(pictureFile);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void prepareMediaRecorder(){
        try {
            File videoFile = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/testvideo.3gp");
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);

            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setVideoSize(1280, 720);
            mediaRecorder.setVideoFrameRate(20);
            mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
            mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
            mediaRecorder.prepare();
            mediaRecorder.start();
            Log.e("zdf", "start recorder");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecorder(){
        mediaRecorder.stop();
        mediaRecorder.release();
    }
}
