package com.example.hellotest.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;

/**
 * 相机类，相机的调用
 */
public class FrontCamera {
    Camera camera;
    int currentCamIndex  = 0;
    boolean previewing;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getCurrentCamIndex() {
        return currentCamIndex;
    }

    public void setCurrentCamIndex(int currentCamIndex) {
        this.currentCamIndex = currentCamIndex;
    }

    public boolean isPreviewing() {
        return previewing;
    }

    public void setPreviewing(boolean previewing) {
        this.previewing = previewing;
    }

    Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback(){
        @Override
        public void onShutter() {

        }
    };

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {

        }
    };

    Camera.PictureCallback jpegPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Log.i("zdf", "已经获取的bitmap:" + bitmap.toString());
            previewing = false;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            camera.startPreview();
            previewing = true;
        }
    };

    //初始化相机
    public Camera initCamera(){
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        previewing = true;

        for(int camIdx  = 0; camIdx < cameraCount; camIdx++){
            Camera.getCameraInfo(camIdx, cameraInfo);
            //在这里打开的是前置，可选择打开后置
            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                cam = Camera.open(camIdx);
                currentCamIndex = camIdx;
            }
        }
        return cam;
    };

    public void StopCamera(Camera camera){
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
        previewing = false;
    };

    public static void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera){
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int ratation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees  = 0;
        switch (ratation){
            case Surface.ROTATION_0:
                degrees  = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if(info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        }else {
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}
