package com.example.yuxuehai.medicalassistan.ui;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.fragment.NavigationDrawerFragment;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

/**
 * Created by yuxuehai on 2017/2/12.
 * 主界面
 */
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private CharSequence mTitle;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_mytb);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);




    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        getSupportActionBar().setHomeButtonEnabled(true);

        //set up the drawer
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout, mToolbar);

        mTitle = getTitle();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
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


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        // 设置显示为标准模式, 还有NAVIGATION_MODE_LIST列表模式, NAVIGATION_MODE_TABS选项卡模式.
        // 参见ApiDemos
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        // 设置显示标题
        actionBar.setDisplayShowTitleEnabled(true);
        // 设置标题
        actionBar.setTitle(mTitle);

        // 1.通过设置自定义内容VIew
        // actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM);
        // actionBar.setDisplayShowHomeEnabled(true);
        // actionBar.setDisplayHomeAsUpEnabled(true);
        // View view = View.inflate(this, R.layout.layout_actionbar, null);
        // actionBar.setCustomView(view);
    }


    @Override
    protected void setupActionBar() {
        super.setupActionBar();

    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }
}
