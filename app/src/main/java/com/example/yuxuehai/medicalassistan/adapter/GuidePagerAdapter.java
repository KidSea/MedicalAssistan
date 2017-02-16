package com.example.yuxuehai.medicalassistan.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-16.
 */

public class GuidePagerAdapter extends PagerAdapter {

    private ArrayList<View> mViewArrayList;


    public GuidePagerAdapter(ArrayList<View> views){
        mViewArrayList = views;
    }


    @Override
    public int getCount() {
        return mViewArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        container.addView(mViewArrayList.get(position));
        return mViewArrayList.get(position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        // super.destroyItem(container, position, object);
        ((ViewPager) (container)).removeView((View) object);
    }
}
