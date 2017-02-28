package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.widget.ClearEditText;
import com.example.yuxuehai.medicalassistan.widget.RoundButton;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class FindPasswdActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ClearEditText mPhone;
    private ClearEditText mVerifcode;
    private ClearEditText mPasswd;
    private ClearEditText mConfirmPasswd;
    private TextView mVerifcode_tv;
    private RoundButton mButton;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_code:

                break;
            case R.id.btn_reset_password:
                break;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_find_passwd);
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mPhone = $(R.id.et_phone);
        mVerifcode = $(R.id.et_code);
        mPasswd = $(R.id.et_new_pass);
        mConfirmPasswd = $(R.id.et_new_password);
        mVerifcode_tv = $(R.id.tv_code);
        mButton = $(R.id.btn_reset_password);

        mButton.setOnClickListener(this);
        mVerifcode_tv.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }
}
