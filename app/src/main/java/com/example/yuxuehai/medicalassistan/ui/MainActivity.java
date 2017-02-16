package com.example.yuxuehai.medicalassistan.ui;


import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

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
        mTitle = getTitle();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search:
                ToastUtil.showToast(this, "Search");
                break;
            case R.id.share:
                ToastUtil.showToast(this, "share");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void setupActionBar() {
        super.setupActionBar();

        ActionBar actionBar = getSupportActionBar();
        // 设置显示为标准模式, 还有NAVIGATION_MODE_LIST列表模式, NAVIGATION_MODE_TABS选项卡模式.
        // 参见ApiDemos
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        // 设置显示标题
        actionBar.setDisplayShowTitleEnabled(true);
        // 设置标题
        actionBar.setTitle(mTitle);
    }
}
