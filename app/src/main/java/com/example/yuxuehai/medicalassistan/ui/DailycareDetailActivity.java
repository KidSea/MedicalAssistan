package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.presenter.impl.DailycarePreseterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.DailycareView;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class DailycareDetailActivity extends BaseActivity implements DailycareView{


    private Toolbar mToolbar;
    private DailycarePreseterDaoImpl mPreseterDao;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dailycare;
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
            actionBar.setTitle(R.string.str_daily);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dailycare_activity_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_add:
                ToastUtil.showToast(this, "add");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_mytb);
    }

    @Override
    protected void initData() {
        mPreseterDao = new DailycarePreseterDaoImpl(this,this);
    }

}
