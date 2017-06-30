package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class ExploreFragment extends BaseFragment implements View.OnClickListener{


    private LinearLayout mFriends;
    private LinearLayout mActivities;
    private LinearLayout mFinds;
    private LinearLayout mScans;
    private LinearLayout mShakes;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_explore, null);
        return view;
    }

    @Override
    protected void initView(View rootView) {

        mFriends = (LinearLayout) rootView.findViewById(R.id.rl_active);
        mActivities = (LinearLayout) rootView.findViewById(R.id.rl_activities);
        mFinds = (LinearLayout) rootView.findViewById(R.id.rl_find_osc);
        mScans = (LinearLayout) rootView.findViewById(R.id.rl_scan);
        mShakes = (LinearLayout) rootView.findViewById(R.id.rl_shake);

        mFriends.setOnClickListener(this);
        mActivities.setOnClickListener(this);
        mFinds.setOnClickListener(this);
        mScans.setOnClickListener(this);
        mShakes.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.rl_active:
                ToastUtil.showToast(getContext(), "护士圈待开发");
                break;
            case R.id.rl_activities:
                ToastUtil.showToast(getContext(), "活动待开发");
                break;
            case R.id.rl_find_osc:
                ToastUtil.showToast(getContext(), "找人待开发");
            case R.id.rl_scan:
                ToastUtil.showToast(getContext(), "扫一扫待开发");
            case R.id.rl_shake:
                ToastUtil.showToast(getContext(), "摇一摇待开发");
            default:
                break;

        }
    }

    public void showClick(int position) {
        ToastUtil.showToast(getContext(), "第" + position + "个Item被点击了");
    }
}
