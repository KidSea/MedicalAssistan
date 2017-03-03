package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyInformationAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.xdandroid.simplerecyclerview.Divider;
import com.xdandroid.simplerecyclerview.SimpleRecyclerView;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class InformationDetailActivity extends BaseActivity {


    private Toolbar mToolbar;
    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleRecyclerView mRecyclerView;
    private MyInformationAdapter mAdapter;

    private ArrayList<String> mList;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_infomation;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_info);
    }


    @Override
    protected void initView() {
        mList = new ArrayList<String>();
        mToolbar = $(R.id.tb_mytb);
        mSwipeRefreshLayout = $(R.id.swipe_container);
        mRecyclerView = $(R.id.recycler_view);

        mSwipeRefreshLayout.setOnRefreshListener(this::newData);
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.postDelayed(() -> {
            setupRecyclerView();
            initData();
        }, 1500);

    }


    @Override
    protected void initData() {

        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            String str = "data" + i + random.nextInt(10);
            mList.add(str);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void newData(){
        mList.clear();
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            String str = "data" + i + random.nextInt(10);
            mList.add(str);
        }
        mAdapter.setList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setupRecyclerView() {
        mRecyclerView.addItemDecoration(new Divider(
                //分割线宽1dp
                UIUtils.dip2px(1),
                //分割线颜色#DDDDDD
                UIUtils.getColor(R.color.graywhite),
                false,
                //分割线左侧留出20dp的空白，不绘制
                UIUtils.dip2px(20), 0, 0, 0)
        );

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyInformationAdapter();
        mAdapter.setList(mList);
        mRecyclerView.setAdapter(mAdapter);

    }


}
