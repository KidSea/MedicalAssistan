package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.ViewPageFragmentAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.widget.EmptyLayout;
import com.example.yuxuehai.medicalassistan.widget.PagerSlidingTabStrip;

/**
 * Created by yuxuehai on 17-2-22.
 */

public abstract class BaseViewPagerframent extends BaseFragment {

    protected PagerSlidingTabStrip mTabStrip; // ViewPager顶部的导航条
    protected ViewPager mViewPager; // 展示内容用的滚动布局ViewPager
    protected ViewPageFragmentAdapter mTabsAdapter; // 封装了数据集合的ViewPager适配器
    protected EmptyLayout mEmptyLayout;// 布局加载异常时, 显示的空布局.
    

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.base_viewpage_fragment, null);

        return view;
    }

    @Override
    protected void initView(View rootView) {
        mTabStrip = (PagerSlidingTabStrip) rootView.findViewById(R.id.vp_indicator);
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_mes_content);
        mEmptyLayout = (EmptyLayout) rootView.findViewById(R.id.layout_empty);


    }

    @Override
    protected void initData() {
        // 封装adapter, 注意这里是继承的FragmentStatePagerAdapter,
        // 并且传入的是getChildFragmentManager()
        // 此处封装了PagerSlidingTabStrip, ViewPager, 在Adapter内部进行一系列的初始化.
        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);

        setScreenPageLimit(mViewPager);

        // 通过ViewPageFragmentAdapter设置Tab选项及内容, 抽象方法, 由子类重写进行实现.
        addPagetoAdapter(mTabsAdapter);
    }

    // 设置viewpafer能够缓存的页数
    protected void setScreenPageLimit(ViewPager mViewPager) {

    }

    // 往adapter添加页数
    protected abstract void addPagetoAdapter(
            ViewPageFragmentAdapter fragmentAdapter);


}
