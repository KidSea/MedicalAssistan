package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.UserBean;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.widget.ClearEditText;
import com.example.yuxuehai.medicalassistan.widget.RoundButton;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class ChangePasswdActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ClearEditText mNowPasswd;
    private ClearEditText mNewPasswd;
    private ClearEditText mNewConfirmPasswd;
    private RoundButton mResetButton;

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {
        onChangePasswd();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_change_passwd);
    }



    @Override
    protected void initView() {

        mToolbar = $(R.id.tb_mytb);
        mNowPasswd = $(R.id.et_now_pass);
        mNewPasswd = $(R.id.et_new_pass);
        mNewConfirmPasswd = $(R.id.et_new_password);
        mResetButton = $(R.id.btn_change_password);

        mResetButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void onChangePasswd() {
        // 1.获取输入框的值
        String now = mNowPasswd.getText().toString().trim();
        String news = mNewPasswd.getText().toString().trim();
        String newPassword = mNewConfirmPasswd.getText().toString();
        // 2.判断是否为空
        if(!TextUtils.isEmpty(now) & !TextUtils.isEmpty(news) & !TextUtils.isEmpty(newPassword)){
            // 3.判断两次新密码是否一致
            if(news.equals(newPassword)) {
                // 4.重置密码
                UserBean.updateCurrentUserPassword(now, news, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null) {
                            ToastUtil.showShort(ChangePasswdActivity.this,
                                    getString(R.string.reset_successfully));
                            finish();
                        } else {
                            ToastUtil.showShort(ChangePasswdActivity.this,
                                    getString(R.string.reset_failed));
                        }
                    }
                });
            }else {
                ToastUtil.showShort(ChangePasswdActivity.this,
                        getString(R.string.text_two_input_not_consistent));
            }
        }else {
            ToastUtil.showShort(ChangePasswdActivity.this, getString(R.string.text_tost_empty));
        }
    }

}
