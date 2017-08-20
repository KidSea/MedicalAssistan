package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.AppManager;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.base.BasePresenterActivity;
import com.example.yuxuehai.medicalassistan.presenter.impl.LoginPresenterImpl;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;
import com.example.yuxuehai.medicalassistan.view.LoginView;
import com.example.yuxuehai.medicalassistan.widget.ClearEditText;
import com.example.yuxuehai.medicalassistan.widget.CustomDialog;

/**
 * Created by yuxuehai on 17-2-27.
 */

public class LoginActivity extends BasePresenterActivity<LoginView,
        LoginPresenterImpl> implements View.OnClickListener, LoginView {


    private Toolbar mToolbar;
    private Button mLoginBuntton;
    private ClearEditText mPhoneNum;
    private ClearEditText mPassWd;
    private CheckBox mIsKeepWd;
    private TextView mFrogetPassWd;
    private CustomDialog mDialog;



    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_forget:
                startActivity(new Intent(this,FindPasswdActivity.class));
                break;
            case R.id.btn_login:
                String phone = mPhoneNum.getText().toString().trim();
                String password = mPassWd.getText().toString().trim();
                // 保存状态
                SharePrefUtil.setBoolean(this, Constants.IsKeepWord, mIsKeepWd.isChecked());
                mPresenter.login(phone, password);
                break;
            default:
                break;
        }

    }

    @Override
    public void setCheckBox(boolean iskeep) {
        mIsKeepWd.setChecked(iskeep);
    }

    @Override
    public void setLoginInfo(String phone, String password) {
        mPhoneNum.setText(phone);
        mPassWd.setText(password);
    }

    @Override
    public void openDialog() {
        mDialog.show();
    }

    @Override
    public void closeDialog() {
        mDialog.dismiss();
    }

    @Override
    public void jumptoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_login);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mLoginBuntton = (Button) findViewById(R.id.btn_login);
        mPhoneNum = (ClearEditText) findViewById(R.id.et_phone);
        mPassWd = (ClearEditText) findViewById(R.id.et_password);
        mIsKeepWd = (CheckBox) findViewById(R.id.keep_password);
        mFrogetPassWd = (TextView) findViewById(R.id.tv_forget);

        mDialog = new CustomDialog(this, 100, 100,
                R.layout.dialog_loading, R.style.Theme_dialog, Gravity.CENTER);
        mDialog.setCancelable(false);

        mFrogetPassWd.setOnClickListener(this);
        mLoginBuntton.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        AppManager.getAppManager().addActivity(this);

        mPresenter.isChecked();

        String phone = SharePrefUtil.getString(this, Constants.sPHONE, "");
        String password = SharePrefUtil.getString(this, Constants.sPASSWORD, "");
        if(!phone.equals("") && !password.equals("")){
            mPhoneNum.setText(phone);
            mPassWd.setText(password);
        }

    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(this);
    }

    /**
     * 假设我现在输入用户名和密码，但是我不点击登录，而是直接退出了
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 保存状态
        SharePrefUtil.setBoolean(this, Constants.IsKeepWord, mIsKeepWd.isChecked());
        // 是否记住密码
        if (mIsKeepWd.isChecked()) {
            // 记住用户名和密码
            SharePrefUtil.setString(this, Constants.sPHONE, mPhoneNum.getText().toString().trim());
            SharePrefUtil.setString(this, Constants.sPASSWORD, mPassWd.getText().toString().trim());
        } else {
            SharePrefUtil.remove(this, Constants.sPHONE);
            SharePrefUtil.remove(this, Constants.sPASSWORD);
        }
    }


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

}
