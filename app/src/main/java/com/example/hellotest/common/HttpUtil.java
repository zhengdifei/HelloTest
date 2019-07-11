package com.example.hellotest.common;

import android.util.Log;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    private static OkHttpClient client = new OkHttpClient();

    public static void HttpGet(String url, Callback callback){
        Log.d("zdf", "http1");
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Log.d("zdf", "http2");
        client.newCall(request).enqueue(callback);
        Log.d("zdf", "http3");
    }

}
