package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyMessagesAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.bean.Notifications;
import com.example.yuxuehai.medicalassistan.presenter.impl.MessagesPresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.example.yuxuehai.medicalassistan.view.MessagesView;
import com.example.yuxuehai.medicalassistan.widget.EmptyLayout;
import com.xdandroid.simplerecyclerview.Divider;
import com.xdandroid.simplerecyclerview.OnItemClickListener;
import com.xdandroid.simplerecyclerview.SimpleRecyclerView;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-22.
 */

public class EmerMesFragment extends BaseFragment implements MessagesView{

    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleRecyclerView mRecyclerView;
    private EmptyLayout mEmptyView;
    private RelativeLayout mNodataLayout;

    private MessagesPresenterDaoImpl mPresenterDao;
    private MyMessagesAdapter mAdapter;


    public <T extends View> T $(View rootView,int id) {
        return (T) rootView.findViewById(id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterDao.getMergMesFromServer();
    }

    @Override
    public void refreshData() {
        mPresenterDao.getMergMesFromServer();
    }

    @Override
    public void setData(ArrayList<Notifications> mList) {
        if (mList.isEmpty()){
            mNodataLayout.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            mAdapter.setList(mList);
            mSwipeRefreshLayout.setRefreshing(false);
            mRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    @Override
    public void showEmpty() {
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mNodataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showView() {
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.VISIBLE);
    }


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, null);
        return view;
    }

    @Override
    protected void initView(View rootView) {
        mPresenterDao = new MessagesPresenterDaoImpl(getContext(), this);

        mSwipeRefreshLayout = $(rootView, R.id.swipe_container);
        mRecyclerView = $(rootView, R.id.recycler_view);
        mEmptyView = $(rootView, R.id.layout_fail);
        mNodataLayout = $(rootView, R.id.layout_empty);

        mEmptyView.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenterDao.getMergMesFromServer();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);

        //添加Divider
        mRecyclerView.addItemDecoration(new Divider(
                //分割线宽1dp
                UIUtils.dip2px(1),
                //分割线颜色#DDDDDD
                UIUtils.getColor(R.color.divider),
                false,
                //分割线左侧留出20dp的空白，不绘制
                UIUtils.dip2px(0), 0, 0, 0));

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(lm);

        mAdapter = new MyMessagesAdapter(getContext()) {
            @Override
            protected void onLoadMore(Void please_make_your_adapter_class_as_abstract_class) {

            }

            @Override
            protected boolean hasMoreElements(Void let_activity_or_fragment_implement_these_methods) {
                return false;
            }

            @Override
            protected int getViewType(int position) {
                return 0;
            }
        };

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, View v, int position, int viewType) {
            }
        });
    }

    @Override
    protected void initData() {

    }

}
