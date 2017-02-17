package com.example.yuxuehai.medicalassistan.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.AppManager;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.utlis.TDevice;

/**
 * Created by yuxuehai on 17-2-4.
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected Context getcontext() {
        return getApplicationContext();
    }

    protected Activity getActivityContext() {
        return this;
    }

    protected abstract int getContentLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected LayoutInflater mInflater;
    protected ActionBar mActionBar;
    private TextView mTvActionTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

        onBeforeSetContentView();


        if(getContentLayoutId() != 0){
            setContentView(getContentLayoutId());
        }

        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();

        if (hasActionBar()) {
            initActionBar(mActionBar);
        }


        initView();
        initData();

        setupActionBar();

    }

    private void initActionBar(ActionBar actionBar) {
        if (actionBar == null)
            return;

        if (hasBackButton()) {
            // 让ActionBar自定义内容
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            int layoutRes = getActionBarCustomView();

            // ------------------------------------------------- 创建自定义布局 ↓
            View view = inflateView(layoutRes == 0 ? R.layout.actionbar_custom_backtitle
                    : layoutRes);
            View back = view.findViewById(R.id.btn_back);
            if (back == null) {
                throw new IllegalArgumentException(
                        "can not find R.id.btn_back in customView");
            }
            back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TDevice.hideSoftKeyboard(getCurrentFocus()); // 隐藏软键盘
                    onBackPressed(); // 按下了返回键
                }
            });
            mTvActionTitle = (TextView) view
                    .findViewById(R.id.tv_actionbar_title);
            if (mTvActionTitle == null) {
                throw new IllegalArgumentException(
                        "can not find R.id.tv_actionbar_title in customView");
            }
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                mTvActionTitle.setText(titleRes);
            }
            ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT);

            // ------------------------------------------------- 创建自定义布局 ↑
            // 设置自定义内容
            actionBar.setCustomView(view, params);
            View spinner = actionBar.getCustomView().findViewById(R.id.spinner);
            if (haveSpinner()) {
                spinner.setVisibility(View.VISIBLE);
            } else {
                spinner.setVisibility(View.GONE);
            }
        } else {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }

    protected int getActionBarCustomView() {
        return 0;
    }

    protected boolean haveSpinner() {
        return false;
    }


    protected void onBeforeSetContentView(){

    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }


    protected boolean hasActionBar() {
        return true;
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected void setupActionBar(){

    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

}
