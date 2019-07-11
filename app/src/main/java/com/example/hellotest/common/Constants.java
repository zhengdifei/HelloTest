package com.example.hellotest.common;

import android.os.Environment;

import java.io.File;

public class Constants {
    public static final String DOWNLOAD_URL = "http://93diy.com/uploads/allimg/1508/1-150Q1133548.jpg";
    public static final String DOWNLOAD_ERROR_URL = "http://93diy.com/uploads/allimg/1508/1-150Q11335481.jpg";

    public static final String LOCAL_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "test.jpg";
}
