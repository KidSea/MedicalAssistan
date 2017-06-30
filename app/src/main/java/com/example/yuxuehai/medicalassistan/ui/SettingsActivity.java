package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.UserBean;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;

import cn.bmob.v3.BmobUser;

/**
 * Created by yuxuehai on 17-3-1.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener {


    private Toolbar mToolbar;
    private LinearLayout mExitView;
    private LinearLayout mChangeView;
    private LinearLayout mAboutView;

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.rl_exit:
                onExitUser();
                break;
            case R.id.rl_change:
                startActivity(new Intent(this, ChangePasswdActivity.class));
                break;
            case R.id.rl_about:
                startActivity(new Intent(this, AboutActivity.class));
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

    private void onExitUser() {

        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout inflate = (RelativeLayout) inflater.inflate(R.layout.dialog_exit, null);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(inflate); // 修改整个dialog窗口的显示

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清除缓存对象
                UserBean.logOut();
                SharePrefUtil.setBoolean(getActivityContext(), Constants.IsLogin, false);
                //System.out.println(UserBean.getCurrentUser());
                // 现在的currentUser是null了
                BmobUser currentUser = UserBean.getCurrentUser();
                //System.out.println(BmobUser.getCurrentUser());
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

}
