package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.GuidePagerAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-16.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager mViewPager;

    private ArrayList<View> mViewArrayList;
    private Button mEntryButton;
    private LinearLayout mPointLayout;
    private int mPonitWidth;
    private View mWhitePoint;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {

        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mEntryButton = (Button) findViewById(R.id.btn_entry);
        mPointLayout = (LinearLayout) findViewById(R.id.ll_point_group);
        mWhitePoint = findViewById(R.id.view_white_point);

        mViewArrayList = new ArrayList<View>();

        LayoutInflater inflater = LayoutInflater.from(this);
        mViewArrayList.add(inflater.inflate(R.layout.activity_guide_one, null));
        mViewArrayList.add(inflater.inflate(R.layout.activity_guide_two, null));
        mViewArrayList.add(inflater.inflate(R.layout.activity_guide_three, null));

        mViewPager.setAdapter(new GuidePagerAdapter(mViewArrayList));

        mEntryButton.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MyPageChangeListener());

        paintRound();

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_entry:
                entryHome();
                break;
            default:
                break;
        }
    }

    private void entryHome() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void paintRound(){
        // 初始化圆点
        for (int i = 0; i < mViewArrayList.size(); i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    UIUtils.dip2px(7), UIUtils.dip2px(7));

            if (i > 0) {
                params.leftMargin = UIUtils.dip2px(10);
            }
            point.setLayoutParams(params);
            mPointLayout.addView(point);
        }

        // 获得视图数
        mPointLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    // 执行完当前的layout后调用该方法
                    @Override
                    public void onGlobalLayout() {
                        // TODO Auto-generated method stub
                        mPointLayout.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                        mPonitWidth = mPointLayout.getChildAt(1).getLeft()
                                - mPointLayout.getChildAt(0).getLeft();
                        System.out.println("圆点距离 :" + mPonitWidth);

                    }
                });
    }


    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int len = (int) (mPonitWidth * positionOffset) + position
                    * mPonitWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mWhitePoint
                    .getLayoutParams();// 获取当前红点的布局参数
            params.leftMargin = len;// 设置左边距

            mWhitePoint.setLayoutParams(params);// 重新给小红点设置布局参数
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 2) {
                mEntryButton.setClickable(true);
                mEntryButton.animate().alpha(1).setDuration(200).start();
            } else {
                mEntryButton.setClickable(false);
                mEntryButton.animate().alpha(0).setDuration(200).start();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
