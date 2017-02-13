package com.example.yuxuehai.medicalassistan.ui;


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
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
    }
}
