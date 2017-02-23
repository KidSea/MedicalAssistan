package com.example.yuxuehai.medicalassistan.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuxuehai.medicalassistan.ui.MainActivity;

/**
 * Created by yuxuehai on 17-2-5.
 */

public abstract class BaseFragment extends Fragment{

    protected MainActivity mActivity;
    private View mMainView = null;

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initView(View rootView);

    protected abstract void initData();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() instanceof MainActivity){
            mActivity = (MainActivity)getActivity();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mMainView == null || mMainView.getParent() != null) {
            mMainView = createView(inflater, container, savedInstanceState);
            initView(mMainView);
            initData();
        }
        return mMainView;
    }


    protected Intent getIntent(){
        return mActivity.getIntent();
    }
}
