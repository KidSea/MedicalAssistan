package com.example.yuxuehai.medicalassistan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.ui.SettingsActivity;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mIv_mine;
    private TextView mTv_phone;
    private TextView mTv_hospital;
    private LinearLayout mSettings;

    public <T extends View> T $(View view, int id) {
        return (T) view.findViewById(id);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_settings:
                getActivity().startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_mine, null);
        return view;
    }

    @Override
    protected void initView(View rootView) {
        mIv_mine = $(rootView, R.id.iv_mine);
        mTv_phone = $(rootView, R.id.tv_phone_num);
        mTv_hospital = $(rootView, R.id.tv_hospital);
        mSettings = $(rootView, R.id.rl_settings);

        mSettings.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mTv_phone.setVisibility(View.VISIBLE);
        mTv_hospital.setVisibility(View.VISIBLE);
    }

}
