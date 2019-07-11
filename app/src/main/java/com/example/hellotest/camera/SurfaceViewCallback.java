package com.example.hellotest.camera;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * 相机界面SurfaceView的回调类
 */
public class SurfaceViewCallback implements SurfaceHolder.Callback, Camera.PreviewCallback {

    Context context;
    FrontCamera frontCamera = new FrontCamera();
    boolean previewing = frontCamera.isPreviewing();
    Camera camera;
    FaceTask faceTask;

    public void setContext(Context context){
        this.context = context;
    }

    /**
     * 相机实时数据的回调
     * @param bytes 相机的数据
     * @param camera 相机的对象
     */
    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        if(faceTask != null){
            switch (faceTask.getStatus()){
                case RUNNING:
                    return;
                case PENDING:
                    faceTask.cancel(false);
                    break;
            }
        }
        faceTask = new FaceTask(bytes, camera);
        faceTask.execute((Void) null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //初始化前置摄像头
        frontCamera.setCamera(camera);
        camera = frontCamera.initCamera();
        camera.setPreviewCallback(this);
        //适配竖排固定角度
        Log.i("zdf", "context:" + context.toString());
        Log.i("zdf", "frontCamera:" + context.toString());
        Log.i("zdf", "camera:" + context.toString());
        FrontCamera.setCameraDisplayOrientation((Activity) context, frontCamera.getCurrentCamIndex(), camera);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(previewing){
            camera.stopPreview();
            Log.i("zdf", "停止预览");
        }

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            camera.setPreviewCallback(this);
            Log.i("zdf", "开始预览");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        frontCamera.StopCamera(camera);
    }
}
