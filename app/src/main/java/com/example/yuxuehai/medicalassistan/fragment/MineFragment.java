package com.example.yuxuehai.medicalassistan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mIv_mine;
    private Button mBtn_login;
    private TextView mTv_phone;
    private TextView mTv_hospital;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_mine, null);
        return view;
    }

    @Override
    protected void initView(View rootView) {
        mIv_mine = (ImageView) rootView.findViewById(R.id.iv_mine);
        mTv_phone = (TextView) rootView.findViewById(R.id.tv_phone_num);
        mTv_hospital = (TextView) rootView.findViewById(R.id.tv_hospital);

    }

    @Override
    protected void initData() {
        mTv_phone.setVisibility(View.VISIBLE);
        mTv_hospital.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            default:
                break;


        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
