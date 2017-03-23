package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;

/**
 * Created by yuxuehai on 17-3-23.
 */

public class ShowDailyEventActivity extends BaseActivity {


    private Toolbar mToolbar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dailycare_event_detail;
    }

    @Override
    protected void initView() {
        mToolbar = bindView(R.id.tb_mytb);

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 设置返回按钮可以点击
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.event_detail);
        }
    }

}
