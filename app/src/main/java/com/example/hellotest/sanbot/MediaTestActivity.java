package com.example.hellotest.sanbot;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hellotest.R;
import com.sanbot.opensdk.base.TopBaseActivity;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.beans.OperationResult;
import com.sanbot.opensdk.function.beans.FaceRecognizeBean;
import com.sanbot.opensdk.function.beans.StreamOption;
import com.sanbot.opensdk.function.unit.HDCameraManager;
import com.sanbot.opensdk.function.unit.interfaces.media.FaceRecognizeListener;
import com.sanbot.opensdk.function.unit.interfaces.media.MediaListener;
import com.sanbot.opensdk.function.unit.interfaces.media.MediaStreamListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaTestActivity extends TopBaseActivity {
    HDCameraManager hdCameraManager;
    List<Integer> handleList = new ArrayList<Integer>();

    @BindView(R.id.media_image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        register(MediaTestActivity.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_test);

        ButterKnife.bind(this);

        hdCameraManager = (HDCameraManager) getUnitManager(FuncConstant.HDCAMERA_MANAGER);
//        hdCameraManager.setMediaListener(new MediaStreamListener() {
//            @Override
//            public void getVideoStream(int i, byte[] bytes, int i1, int i2) {
//                //Log.e("zdf video:", new String(bytes));
//            }
//
//            @Override
//            public void getAudioStream(int i, byte[] bytes) {
//                //Log.e("zdf audio:", new String(bytes));
//            }
//        });

        hdCameraManager.setMediaListener(new FaceRecognizeListener() {
            @Override
            public void recognizeResult(List<FaceRecognizeBean> list) {
                for(int i= 0; i < list.size(); i++){
                    Log.e("zdf face:", list.get(i).getW() + "");
                }
            }
        });
    }

    @Override
    protected void onMainServiceConnected() {
        Log.d("zdf", "meida begin");
        Glide.with(this).load("http://img1.3lian.com/img013/v4/96/d/45.jpg").into(image);
    }

    @OnClick({R.id.media_btn1, R.id.media_btn2, R.id.media_btn3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.media_btn1:
                StreamOption streamOption = new StreamOption();
                streamOption.setChannel(StreamOption.MAIN_STREAM);
                streamOption.setDecodType(StreamOption.HARDWARE_DECODE);
                streamOption.setJustIframe(false);
                OperationResult operationResult = hdCameraManager.openStream(streamOption);
                int result = Integer.valueOf(operationResult.getResult());
                if(result != -1){
                    handleList.add(result);
                }
                break;
            case R.id.media_btn2:
                for(int i =0;i< handleList.size();i++){
                    hdCameraManager.closeStream(handleList.get(i));
                }
                break;
            case R.id.media_btn3:
                Bitmap one = hdCameraManager.getVideoImage();
                Log.e("zdf get image:", one.toString());
                image.setImageBitmap(one);
                break;
            default:
                break;
        }
    }
}
