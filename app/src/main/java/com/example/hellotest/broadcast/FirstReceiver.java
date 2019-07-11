package com.example.hellotest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FirstReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String time = intent. getStringExtra("date");
        Toast.makeText(context, "receive time is " + time, Toast.LENGTH_SHORT).show();
    }
}
