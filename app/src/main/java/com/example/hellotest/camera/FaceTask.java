package com.example.hellotest.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * 单独的任务类，继承AsyncTask，处理从相机实时获取的耗时操作
 */
public class FaceTask extends AsyncTask {

    private byte[] data;
    Camera camera;

    public FaceTask(byte[] data, Camera camera){
        this.data = data;
        this.camera = camera;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Camera.Parameters parameters = camera.getParameters();
        int imageFormat = parameters.getPreviewFormat();
        int w = parameters.getPreviewSize().width;
        int h = parameters.getPreviewSize().height;

        Rect rect = new Rect(0, 0, w ,h);
        YuvImage yuvImage = new YuvImage(data, imageFormat, w, h, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 100, outputStream);
        Bitmap bitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size());
        Log.i("zdf", "onPreviewFrame； " + bitmap.toString());

        return null;
    }
}
