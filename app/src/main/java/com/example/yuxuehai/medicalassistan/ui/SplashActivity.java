package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;

/**
 * Created by yuxuehai on 2017/2/12.
 */

public class SplashActivity extends BaseActivity {


    private View mView;

    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    protected void initView() {
    }


    protected void initData() {
        mView = View.inflate(this, getContentLayoutId(), null);
        setContentView(mView);


        AlphaAnimation ap = new AlphaAnimation(0.3f, 1.0f);
        ap.setDuration(3000);
        mView.startAnimation(ap);
        ap.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                redirectTo();
            }
        });

    }


    protected void redirectTo() {
        // TODO Auto-generated method stub
        boolean isfirst = SharePrefUtil.getBoolean(this, Constants.sIsFirstRun, false);

        if (!isfirst) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
