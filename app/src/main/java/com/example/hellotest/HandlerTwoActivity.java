package com.example.hellotest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HandlerTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_two);

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 200;
                msg.obj = "from activity 2";
                Handler handler = new MyHandler();
                Log.e("zdf", "onClick: looper=="+handler.getLooper() );
                Log.e("zdf", "onClick: messageQueue=="+handler.getLooper().getQueue() );
                handler.sendMessage(msg);
            }
        });
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String info = "";
            switch (msg.what){
                case 100:
                    info = (String)msg.obj;
                    Log.e("zdf", "handlerMessage info:" + info);
                    break;
//                case 200:
//                    info = (String)msg.obj;
//                    Log.e("zdf", "handlerMessage act-1-info:" + info);
//                    break;
                default:
                    break;
            }
        }
    }
}
