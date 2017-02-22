package com.example.yuxuehai.medicalassistan.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.ViewPageInfo;
import com.example.yuxuehai.medicalassistan.widget.PagerSlidingTabStrip;

import java.util.ArrayList;




@SuppressLint("Recycle")
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    protected PagerSlidingTabStrip mPagerStrip;
    private final ViewPager mViewPager;
    private final ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();



	public ViewPageFragmentAdapter(FragmentManager fm,
            PagerSlidingTabStrip pageStrip, ViewPager pager) {
        super(fm);
        mContext = pager.getContext();
        mPagerStrip = pageStrip;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mPagerStrip.setViewPager(mViewPager);
    }

    public void addTab(String title, String tag, Class<?> clss, Bundle args) {
        ViewPageInfo viewPageInfo = new ViewPageInfo(title, tag, clss, args);
        addFragment(viewPageInfo);
    }

    public void addAllTab(ArrayList<ViewPageInfo> mTabs) {
        for (ViewPageInfo viewPageInfo : mTabs) {
            addFragment(viewPageInfo);
        }
    }

    private void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }

        // 加入tab title
        View v = LayoutInflater.from(mContext).inflate(
                R.layout.base_viewpage_fragment_tab_item, null, false);
        TextView title = (TextView) v.findViewById(R.id.tab_title);
        title.setText(info.title);
        mPagerStrip.addTab(v);

        mTabs.add(info);
        notifyDataSetChanged();
    }


    /**
     * 移除所有的tab
     */
    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mPagerStrip.removeAllTab();
        mTabs.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }
}