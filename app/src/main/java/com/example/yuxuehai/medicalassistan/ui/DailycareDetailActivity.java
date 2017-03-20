package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyDragSwipAdapter;
import com.example.yuxuehai.medicalassistan.adapter.SwipeMenuAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.dao.OnMenuClickListener;
import com.example.yuxuehai.medicalassistan.presenter.impl.DailycarePreseterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.DailycareView;
import com.example.yuxuehai.medicalassistan.widget.PtrSwipeMenuRecyclerView;

import java.util.List;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class DailycareDetailActivity extends BaseActivity implements DailycareView, OnMenuClickListener{


    private Toolbar mToolbar;
    private RelativeLayout mProgress;
    private RelativeLayout mNoData;
    private PtrSwipeMenuRecyclerView mRecyclerView;

    private DailycarePreseterDaoImpl mPreseterDao;
    private MyDragSwipAdapter mAdapter;

    private List<Integer> mList;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dailycare_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_add:
                Intent intent = new Intent(this, DailycareResponseActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPrograss() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrograss() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onMenuClick(View view, int position) {
        if (view.getId() == R.id.tv_edit)
            ToastUtil.showToast(this,"position:" + position + ",menu1");
        if (view.getId() == R.id.tv_delete)
            ToastUtil.showToast(this,"position:" + position + ",menu2");
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mProgress = $(R.id.pb_loding);
        mRecyclerView = $(R.id.rc_dailyview);
        mNoData = $(R.id.layout_empty);
    }

    @Override
    protected void initData() {

        mAdapter = new MyDragSwipAdapter();
        mAdapter.setOnItemClickListener(new SwipeMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, View v, int position) {
                ToastUtil.showToast(getcontext(), "第" + position + "个Item被点击了");
            }
        });


        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setPullToRefreshEnable(false);
        mRecyclerView.setPullLoadMoreEnable(false);
        //添加菜单点击事件
        mRecyclerView.setOnMenuClickListener(this);



        mPreseterDao = new DailycarePreseterDaoImpl(this, this);

        mList = mPreseterDao.getDataFromServer();

        hidePrograss();

        mAdapter.setDatas(mList);

    }

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



}
