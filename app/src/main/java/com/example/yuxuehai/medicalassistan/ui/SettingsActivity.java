package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by yuxuehai on 17-3-1.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener{


    private Toolbar mToolbar;
    private LinearLayout mExitView;
    private LinearLayout mChangeView;
    private LinearLayout mAboutView;

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.rl_exit:
                BmobUser.getCurrentUser().logOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.rl_change:
                startActivity(new Intent(this, ChangePasswdActivity.class));
                break;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_settings);
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mExitView = $(R.id.rl_exit);
        mChangeView = $(R.id.rl_change);
        mAboutView = $(R.id.rl_about);

        mExitView.setOnClickListener(this);
        mChangeView.setOnClickListener(this);
        mAboutView.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }


}
