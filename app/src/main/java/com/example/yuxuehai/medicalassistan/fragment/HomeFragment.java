package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.dao.AbOnItemClickListener;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.example.yuxuehai.medicalassistan.widget.AbSlidingPlayView;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class HomeFragment extends BaseFragment {

    /**首页轮播的界面的资源*/
    private int[] resId = {R.drawable.photo_one,R.drawable.photo_two,R.drawable.photo_three,R.drawable.photo_five,R.drawable.photo_six};
    private AbSlidingPlayView mPlayView;
    /**存储首页轮播的界面*/
    private ArrayList<View> allListView;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, null);

        return view;
    }

    @Override
    protected void initView(View rootView) {
        mPlayView = (AbSlidingPlayView) rootView.findViewById(R.id.viewPager_menu);

        //设置播放方式为顺序播放
        mPlayView.setPlayType(1);
        //设置播放间隔时间
        mPlayView.setSleepTime(3000);

        initViewPager();
    }

    @Override
    protected void initData() {

    }


    private void initViewPager() {

        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            //导入ViewPager的布局
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            imageView.setImageResource(resId[i]);
            allListView.add(view);
        }


        mPlayView.addViews(allListView);
        //开始轮播
        mPlayView.startPlay();
        mPlayView.setOnItemClickListener(new AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtil.showToast(UIUtils.getContext(),"第"+ position +"个页面被点击了");
            }
        });
    }
}
