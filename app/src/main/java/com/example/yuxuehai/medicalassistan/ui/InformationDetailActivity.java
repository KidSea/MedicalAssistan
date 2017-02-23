package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class InformationDetailActivity extends BaseActivity {


    private Toolbar mToolbar;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_infomation;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_info);
    }


    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_mytb);

    }


    @Override
    protected void initData() {
    }


}
