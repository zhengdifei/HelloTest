package com.example.hellotest.broadcast;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.InflateException;
import android.view.View;
import android.widget.TextView;

import com.example.hellotest.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private static final String MY_ACTION = "broadcast";
    private static final String MY_ACTION_1 = "broadcast_1";
    private static final String MY_ACTION_LOCAL = "broadcast_local";

    private Context context;

    private FirstReceiver firstReceiver;
    private SecondReceiver secondReceiver;
    private ThirdReceiver thirdReceiver;

    private LocalBroadcastManager localBroadcastManager;

    private TextView battery;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        context = this;
        firstReceiver = new FirstReceiver();
        secondReceiver = new SecondReceiver();
        thirdReceiver = new ThirdReceiver();

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日    HH:mm:ss", Locale.CHINA);
                intent.putExtra("date", simpleDateFormat.format(new Date()));
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.sendSecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MY_ACTION_1);
                intent.putExtra("msg", String.valueOf(System.currentTimeMillis()));
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.sendLocal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localBroadcastManager = LocalBroadcastManager.getInstance(context);
                localBroadcastManager.registerReceiver(thirdReceiver, new IntentFilter(MY_ACTION_1));
                Intent intent = new Intent();
                intent.setAction(MY_ACTION_LOCAL);
                intent.putExtra("msg", "this is local broadcast");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        getBattery();
    }

    private void getBattery(){
        battery = findViewById(R.id.battery);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryIntent = getApplicationContext().registerReceiver(null, intentFilter);
        int total = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
        int current = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        float percent = current * 100 / total;
        battery.setText(String.valueOf(percent));
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_ACTION);
        registerReceiver(firstReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(MY_ACTION_1);
        registerReceiver(secondReceiver, intentFilter1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(firstReceiver);
        unregisterReceiver(secondReceiver);

        if(localBroadcastManager != null){
            localBroadcastManager.unregisterReceiver(thirdReceiver);
        }
    }
}
