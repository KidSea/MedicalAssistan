package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyPatientsInformationAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.example.yuxuehai.medicalassistan.bean.Ward;
import com.example.yuxuehai.medicalassistan.presenter.impl.PatientsPresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.example.yuxuehai.medicalassistan.view.InformationView;
import com.example.yuxuehai.medicalassistan.widget.EmptyLayout;
import com.xdandroid.simplerecyclerview.Divider;
import com.xdandroid.simplerecyclerview.OnItemClickListener;
import com.xdandroid.simplerecyclerview.SimpleRecyclerView;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-3-6.
 */

public class PatientsInfoActivity extends BaseActivity implements InformationView {


    private Ward mWard;

    private Toolbar mToolbar;
    private EmptyLayout mEmptyView;
    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleRecyclerView mRecyclerView;


    private PatientsPresenterDaoImpl mPresenterDao;
    private MyPatientsInformationAdapter mAdapter;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void refreshData() {
        mPresenterDao.getPatientsFromServer(mWard);
    }

    @Override
    public void setData(ArrayList<SampleBean> mList) {
        mAdapter.setList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        mWard = (Ward) intent.getSerializableExtra("ward");
        mPresenterDao = new PatientsPresenterDaoImpl(this,this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
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

        mSwipeRefreshLayout = $(R.id.swipe_container);
        mRecyclerView = $(R.id.recycler_view);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);

        mEmptyView = $(R.id.layout_empty);

        mEmptyView.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenterDao.getPatientsFromServer(mWard);
            }
        });

        //添加Divider
        mRecyclerView.addItemDecoration(new Divider(
                //分割线宽1dp
                UIUtils.dip2px(1),
                //分割线颜色#DDDDDD
                 UIUtils.getColor(R.color.divider),
                false,
                //分割线左侧留出20dp的空白，不绘制
                UIUtils.dip2px(0), 0, 0, 0));

        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);

        mAdapter = new MyPatientsInformationAdapter(this) {
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
                ToastUtil.showToast(getcontext(),"第"+position+"个item被点击了");
                Patient patient = mAdapter.getBean(position);
                Intent intent = new Intent(PatientsInfoActivity.this, PatientsDetailActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenterDao.getPatientsFromServer(mWard);
    }

}
