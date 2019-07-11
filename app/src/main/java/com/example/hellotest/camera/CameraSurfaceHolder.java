package com.example.hellotest.camera;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 相机界面SurfaceView的Holder类
 */
public class CameraSurfaceHolder {
    Context context;
    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    SurfaceViewCallback callback = new SurfaceViewCallback();

    public void setCameraSurfaceHolder(Context context, SurfaceView surfaceView){
        this.context = context;
        this.surfaceView = surfaceView;

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(callback);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        callback.setContext(context);
    }
}
