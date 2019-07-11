package com.example.hellotest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LifecycleActivity extends AppCompatActivity {

    @BindViews({R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    List<Button> btns;

    @BindString(R.string.app_name)
    String appName;

    @BindArray(R.array.weather)
    String[] weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        ButterKnife.bind(this);


//        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SystemClock.sleep(7000);
//            }
//        });

//        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LifecycleActivity.this, GlideActivity.class));
//            }
//        });

//        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

//        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(LifecycleActivity.this);
//                builder.setTitle("dialog on activity");
//                builder.setMessage("some content on dialog");
//                builder.setPositiveButton("sure", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.show();
//            }
//        });
    }

    @OnClick({R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button2:
                Log.e("zdf", "test button2");
                Toast.makeText(this, "测试点击第一个button2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Log.e("zdf", "test button3");
                Toast.makeText(this, "测试点击第一个button3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                Log.e("zdf", "test button4");
                Toast.makeText(this, "测试点击第一个button4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                Log.e("zdf", "test button5");
                Toast.makeText(this, "测试点击第一个button5", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zdf", "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zdf", "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zdf", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zdf", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zdf", "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zdf", "onRestart: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("zdf", "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("zdf", "onRestoreInstanceState: ");
    }
}
