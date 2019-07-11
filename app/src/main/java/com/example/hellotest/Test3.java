package com.example.hellotest;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test3 extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test3);
        context = this;
        init3();
    }

    public void init(){
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("zdf init1", Thread.currentThread().getName());
                        Toast.makeText(context, "I am Handler", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
    }

    public void init1(){
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("zdf", "thread run");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(2000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        Log.d("zdf init", Thread.currentThread().getName());
                        Toast.makeText(context, "thread run", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void init2(){
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("zdf", "onclick " + Runtime.getRuntime().availableProcessors());
                for(int i = 0;i < 137;i++){
                    //串行执行
                    //new MyAsyncTask().execute("Task:" + i);
                    //并行执行
                    new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "AsyncTask:" + i);
                }
            }
        });
    }

    public void init3(){
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(wifiManager != null && wifiManager.isWifiEnabled()){
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo != null){
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if(state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR){
                    Toast.makeText(context, wifiInfo.getSSID(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress();
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
            //Log.w("zdf", s + " execute finish at " + sdf.format(new Date()));
        }
    }
}
