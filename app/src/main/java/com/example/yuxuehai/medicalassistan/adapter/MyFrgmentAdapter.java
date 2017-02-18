package com.example.yuxuehai.medicalassistan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuxuehai.medicalassistan.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class MyFrgmentAdapter extends FragmentPagerAdapter {

    ArrayList<BaseFragment> mFragments;

    public MyFrgmentAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return (mFragments == null || mFragments.size() == 0) ? null
                : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
