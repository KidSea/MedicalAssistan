package com.example.yuxuehai.medicalassistan.ui;


import android.app.Activity;

import com.example.yuxuehai.medicalassistan.AppManager;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;

/**
 * Created by yuxuehai on 2017/2/12.
 * 主界面
 */
public class MainActivity extends BaseActivity {

    private CharSequence mTitle;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitle = getTitle();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void beforeView() {
        super.beforeView();
        //防止第三方跳转出现双实例
        Activity aty = AppManager.getActivity(MainActivity.class);
        if(aty != null && !aty.isFinishing()){
            finish();
        }
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
    }
}
