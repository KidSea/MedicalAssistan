package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

/**
 * Created by yuxuehai on 17-3-20.
 */

public class DailycareResponseActivity extends BaseActivity {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dailycare_res_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_submit:
                ToastUtil.showToast(this, "submit");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dailycare_respond;
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
            actionBar.setTitle(R.string.new_event);
        }
    }

    @Override
    protected void initView() {
        mToolbar = bindView(R.id.tb_mytb);
    }

    @Override
    protected void initData() {

    }

}
