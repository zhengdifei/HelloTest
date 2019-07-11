package com.example.hellotest.sanbot;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.hellotest.R;
import com.sanbot.opensdk.base.BindBaseActivity;
import com.sanbot.opensdk.base.TopBaseActivity;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.function.beans.handmotion.AbsoluteAngleHandMotion;
import com.sanbot.opensdk.function.beans.handmotion.NoAngleHandMotion;
import com.sanbot.opensdk.function.beans.handmotion.RelativeAngleHandMotion;
import com.sanbot.opensdk.function.unit.HandMotionManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandTestActivity extends TopBaseActivity {

    HandMotionManager handMotionManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        register(HandTestActivity.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_test);

        ButterKnife.bind(this);
        handMotionManager = (HandMotionManager) getUnitManager(FuncConstant.HANDMOTION_MANAGER);
    }

    @Override
    protected void onMainServiceConnected() {
        Log.d("zdf", "begin sport");
    }

    @OnClick({R.id.hand_btn1, R.id.hand_btn2, R.id.hand_btn3, R.id.hand_btn4, R.id.hand_btn5, R.id.hand_btn6, R.id.hand_btn7, R.id.hand_btn8, R.id.hand_btn9})
    public void onClick(View view){
        NoAngleHandMotion noAngleHandMotion;
        RelativeAngleHandMotion relativeAngleHandMotion;
        AbsoluteAngleHandMotion absoluteAngleHandMotion;
        switch (view.getId()){
            case R.id.hand_btn1:
                noAngleHandMotion = new NoAngleHandMotion(NoAngleHandMotion.PART_BOTH, 5, NoAngleHandMotion.ACTION_UP);
                handMotionManager.doNoAngleMotion(noAngleHandMotion);
                break;
            case R.id.hand_btn2:
                noAngleHandMotion = new NoAngleHandMotion(NoAngleHandMotion.PART_BOTH, 10, NoAngleHandMotion.ACTION_DOWN);
                handMotionManager.doNoAngleMotion(noAngleHandMotion);
                break;
            case R.id.hand_btn3:
                noAngleHandMotion = new NoAngleHandMotion(NoAngleHandMotion.PART_BOTH, 1, NoAngleHandMotion.ACTION_RESET);
                handMotionManager.doNoAngleMotion(noAngleHandMotion);
                break;
            case R.id.hand_btn4:
                relativeAngleHandMotion = new RelativeAngleHandMotion(NoAngleHandMotion.PART_BOTH, 3, NoAngleHandMotion.ACTION_UP, 30);
                handMotionManager.doRelativeAngleMotion(relativeAngleHandMotion);
                break;
            case R.id.hand_btn5:
                relativeAngleHandMotion = new RelativeAngleHandMotion(NoAngleHandMotion.PART_BOTH, 5, NoAngleHandMotion.ACTION_DOWN, 50);
                handMotionManager.doRelativeAngleMotion(relativeAngleHandMotion);
                break;
            case R.id.hand_btn6:
                relativeAngleHandMotion = new RelativeAngleHandMotion(NoAngleHandMotion.PART_BOTH, 8, NoAngleHandMotion.ACTION_DOWN, 270);
                handMotionManager.doRelativeAngleMotion(relativeAngleHandMotion);
                break;
            case R.id.hand_btn7:
                absoluteAngleHandMotion = new AbsoluteAngleHandMotion(NoAngleHandMotion.PART_BOTH, 8, 30);
                handMotionManager.doAbsoluteAngleMotion(absoluteAngleHandMotion);
                break;
            case R.id.hand_btn8:
                absoluteAngleHandMotion = new AbsoluteAngleHandMotion(NoAngleHandMotion.PART_BOTH, 8,50);
                handMotionManager.doAbsoluteAngleMotion(absoluteAngleHandMotion);
                break;
            case R.id.hand_btn9:
                absoluteAngleHandMotion = new AbsoluteAngleHandMotion(NoAngleHandMotion.PART_BOTH, 8,  270);
                handMotionManager.doAbsoluteAngleMotion(absoluteAngleHandMotion);
                break;
            default:
                break;
        }
    }
}
