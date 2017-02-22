package com.example.yuxuehai.medicalassistan.fragment;

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

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_mine, null);
        return view;
    }

    @Override
    protected void initView(View rootView) {
        mIv_mine = (ImageView) rootView.findViewById(R.id.iv_mine);
        mBtn_login = (Button) rootView.findViewById(R.id.btn_login);
        mTv_phone = (TextView) rootView.findViewById(R.id.tv_phone_num);

        mBtn_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                mBtn_login.setVisibility(View.GONE);
                mTv_phone.setVisibility(View.VISIBLE);
                break;
            default:
                break;


        }
    }
}
