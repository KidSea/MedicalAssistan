package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyDailyCareAdapter;
import com.example.yuxuehai.medicalassistan.adapter.MyGrideViewAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.bean.ItemData;
import com.example.yuxuehai.medicalassistan.dao.AbOnItemClickListener;
import com.example.yuxuehai.medicalassistan.presenter.impl.HomePresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.example.yuxuehai.medicalassistan.view.HomeView;
import com.example.yuxuehai.medicalassistan.widget.AbSlidingPlayView;
import com.example.yuxuehai.medicalassistan.widget.Decoration;
import com.example.yuxuehai.medicalassistan.widget.MyGridView;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class HomeFragment extends BaseFragment implements HomeView,AdapterView.OnItemClickListener{

    private AbSlidingPlayView mPlayView;
    /**
     * 存储首页轮播的界面
     */

    private MyGridView mGridView;
    private MyGrideViewAdapter mGrideViewAdapter;
    private ArrayList<ItemData> mItemDatas;
    private HomePresenterDaoImpl mHomePresenterDao;
    private RecyclerView mDailyCareView;
    private RecyclerView mTopNewsView;
    private RecyclerView mRecommendView;
    private ArrayList<String> mList;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_home, null);

        return view;
    }

    @Override
    protected void initView(View rootView) {
        mHomePresenterDao = new HomePresenterDaoImpl(getContext(),this);
        mItemDatas = new ArrayList<>();
        mPlayView = (AbSlidingPlayView) rootView.findViewById(R.id.viewPager_menu);
        mGridView = (MyGridView) rootView.findViewById(R.id.my_gridview);
        mDailyCareView = (RecyclerView) rootView.findViewById(R.id.recycler_dailycare);
        mTopNewsView = (RecyclerView) rootView.findViewById(R.id.recycler_topnews);
        mRecommendView = (RecyclerView) rootView.findViewById(R.id.recycler_recommend);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mDailyCareView.setLayoutManager(layoutManager);
        mDailyCareView.addItemDecoration(new Decoration(getContext(), LinearLayout.VERTICAL));


        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTopNewsView.setLayoutManager(layoutManager1);

        mTopNewsView.addItemDecoration(new Decoration(getContext(), LinearLayout.VERTICAL));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRecommendView.setHasFixedSize(true);
        mRecommendView.setLayoutManager(gridLayoutManager);

        mGrideViewAdapter = new MyGrideViewAdapter(mItemDatas, getContext());
        mGridView.setOnItemClickListener(this);

        //设置播放方式为顺序播放
        mPlayView.setPlayType(1);
        //设置播放间隔时间
        mPlayView.setSleepTime(3000);

    }

    @Override
    protected void initData() {

        mList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            String str = "data" + i;
            mList.add(str);
        }

        mHomePresenterDao.initIcons(mItemDatas);
        mHomePresenterDao.initAds();

        mGridView.setAdapter(mGrideViewAdapter);
        mDailyCareView.setAdapter(new MyDailyCareAdapter(getContext(),mList));
        mTopNewsView.setAdapter(new MyDailyCareAdapter(getContext(),mList));
        mRecommendView.setAdapter(new MyDailyCareAdapter(getContext(),mList));

    }





    @Override
    public void startAd(ArrayList<View> allListView) {

        mPlayView.addViews(allListView);
        //开始轮播
        mPlayView.startPlay();
        mPlayView.setOnItemClickListener(new AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtil.showToast(UIUtils.getContext(), "第" + position + "个页面被点击了");
            }
        });

    }

    @Override
    public void showClick(int position) {
        ToastUtil.showToast(getContext(),"第" + position + "个Item被点击了");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mHomePresenterDao.onclickCall(position);
    }
}
