package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyInformationAdapter;
import com.example.yuxuehai.medicalassistan.base.BasePresenterActivity;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.example.yuxuehai.medicalassistan.bean.Ward;
import com.example.yuxuehai.medicalassistan.presenter.impl.InformationPresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.view.InformationView;
import com.example.yuxuehai.medicalassistan.widget.EmptyLayout;
import com.example.yuxuehai.medicalassistan.widget.MyGridLayoutManager;
import com.xdandroid.simplerecyclerview.OnItemClickListener;
import com.xdandroid.simplerecyclerview.SimpleRecyclerView;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class InformationDetailActivity extends BasePresenterActivity<InformationView
        , InformationPresenterDaoImpl> implements InformationView{


    private Toolbar mToolbar;
    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleRecyclerView mRecyclerView;
    private MyInformationAdapter mAdapter;
    private ArrayList<SampleBean> mList;

    private EmptyLayout mEmptyView;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void setData(ArrayList<SampleBean> mList) {
        mAdapter.setList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
        ((MyGridLayoutManager)mRecyclerView.getLayoutManager()).setScrollEnabled(true);
        mRecyclerView.getLayoutManager().scrollToPosition(0);
    }

    @Override
    public void showEmpty() {
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showView() {
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }


    @Override
    public void refreshData() {
        mPresenter.getListFromServer();
        ((MyGridLayoutManager)mRecyclerView.getLayoutManager()).setScrollEnabled(false);
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

        mToolbar = $(R.id.tb_mytb);
        mSwipeRefreshLayout = $(R.id.swipe_container);
        mRecyclerView = $(R.id.recycler_view);
        mEmptyView = $(R.id.layout_empty);

        mEmptyView.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getListFromServer();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);


        MyGridLayoutManager gm = new MyGridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gm);
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
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, View v, int position, int viewType) {
                //ToastUtil.showToast(getcontext(),"第"+position+"个item被点击了");
                Ward ward = mAdapter.getBean(position);
                Intent intent = new Intent(InformationDetailActivity.this, PatientsInfoActivity.class);
                intent.putExtra("ward", ward);
                startActivity(intent);
            }
        });

    }



    @Override
    protected void initData() {
        mPresenter.getListFromServer();
        //mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected InformationPresenterDaoImpl createPresenter() {
        return new InformationPresenterDaoImpl(this);
    }

}
