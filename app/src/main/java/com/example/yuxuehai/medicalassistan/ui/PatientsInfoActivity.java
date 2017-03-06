package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.example.yuxuehai.medicalassistan.bean.Ward;
import com.example.yuxuehai.medicalassistan.presenter.impl.InformationPresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.view.InformationView;
import com.example.yuxuehai.medicalassistan.widget.MyGridLayoutManager;
import com.xdandroid.simplerecyclerview.SimpleRecyclerView;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-3-6.
 */

public class PatientsInfoActivity extends BaseActivity implements InformationView {


    private Ward mWard;

    private TextView mTextView;
    private Toolbar mToolbar;
    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleRecyclerView mRecyclerView;


    private InformationPresenterDaoImpl mPresenterDao;




    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void setData(ArrayList<SampleBean> mList) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        mWard = (Ward) intent.getSerializableExtra("ward");
        mPresenterDao = new InformationPresenterDaoImpl(this,this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(mWard.getRoomName()+"病房");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_patients_info;
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        //mTextView = $(R.id.tv_wardId);
        mTextView.setText(mWard.getObjectId());

        mSwipeRefreshLayout = $(R.id.swipe_container);
        mRecyclerView = $(R.id.recycler_view);

        mSwipeRefreshLayout.setOnRefreshListener(this::refreshData);
        mSwipeRefreshLayout.setRefreshing(true);

        MyGridLayoutManager gm = new MyGridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gm);


    }

    @Override
    protected void initData() {
        mPresenterDao.getPatientsFromServer(mWard);
    }

}
