package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.Constants;

/**
 * Created by yuxuehai on 17-2-27.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{


    private Toolbar mToolbar;
    private Button mLoginBuntton;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
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
        mLoginBuntton = (Button) findViewById(R.id.btn_login);
        mLoginBuntton.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View view) {
        LoginActivity.this.setResult(Constants.RESULT_UPDATE_INFO, new Intent());
        finish();
    }
}
