package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;

/**
 * Created by yuxuehai on 17-2-22.
 */

public class DefaultFragment extends BaseFragment {


    private TextView mViewById;
    private String mParam1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("key");
        }
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_default, null);
        return view;
    }

    @Override
    protected void initView(View rootView) {
        mViewById = (TextView) rootView.findViewById(R.id.tv_content);

        if(TextUtils.isEmpty(mParam1)){
            mViewById.setText("我是一个测试用的Fragment, 我创建的时候没有传进来Bundle, 所以显示这个内容.");
        }else {
            mViewById.setText(mParam1);
        }

    }

    @Override
    protected void initData() {

    }
}
