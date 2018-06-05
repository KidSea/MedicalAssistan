package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;

import static com.example.yuxuehai.medicalassistan.utlis.PermissionUtils.REQUEST_CODE;
import static com.example.yuxuehai.medicalassistan.utlis.PermissionUtils.hasAllPermissionsGranted;
import static com.example.yuxuehai.medicalassistan.utlis.PermissionUtils.lacksPermissions;
import static com.example.yuxuehai.medicalassistan.utlis.PermissionUtils.permissions;

/**
 * Created by yuxuehai on 2017/2/12.
 */

public class SplashActivity extends BaseActivity {


    private View mView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            redirectTo();
        } else {
            delayFinish();
        }
    }

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
                //动态申请权限，Android6.0以上使用
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && lacksPermissions(permissions)) {
                    requestPermissions(permissions, REQUEST_CODE);
                }else {
                    redirectTo();
                }
            }
        });

    }


    protected void redirectTo() {
        // TODO Auto-generated method stub
        boolean isFirst = SharePrefUtil.getBoolean(this, Constants.IsFirstRun, false);
        boolean isLogin = SharePrefUtil.getBoolean(this, Constants.IsLogin, false);

        if(isLogin){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            if (!isFirst) {
                Intent intent = new Intent(this, GuideActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
    private void delayFinish() {
        new Handler().postDelayed(SplashActivity.this::finish, 1000);
    }


}
