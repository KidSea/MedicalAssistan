package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyInformationAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.example.yuxuehai.medicalassistan.presenter.impl.InformationPresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.example.yuxuehai.medicalassistan.view.InformationView;
import com.xdandroid.simplerecyclerview.Divider;
import com.xdandroid.simplerecyclerview.SimpleRecyclerView;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class InformationDetailActivity extends BaseActivity implements InformationView{


    private Toolbar mToolbar;
    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleRecyclerView mRecyclerView;
    private MyInformationAdapter mAdapter;

    private InformationPresenterDaoImpl mPresenterDao;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void setData(ArrayList<SampleBean> mList) {
        mAdapter.setList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void refreshData() {
        mPresenterDao.getListFromServer();
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
        mPresenterDao = new InformationPresenterDaoImpl(this,this);

        mToolbar = $(R.id.tb_mytb);
        mSwipeRefreshLayout = $(R.id.swipe_container);
        mRecyclerView = $(R.id.recycler_view);

        mSwipeRefreshLayout.setOnRefreshListener(this::refreshData);
        mSwipeRefreshLayout.setRefreshing(true);

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
        mAdapter = new MyInformationAdapter(){

            @Override
            protected void onLoadMore(Void please_make_your_adapter_class_as_abstract_class) {
            }

            @Override
            protected boolean hasMoreElements(Void let_activity_or_fragment_implement_these_methods) {
                return false;
            }
        };
        mRecyclerView.setAdapter(mAdapter);


    }



    @Override
    protected void initData() {
        mPresenterDao.getListFromServer();
        //mSwipeRefreshLayout.setRefreshing(false);
    }

}
