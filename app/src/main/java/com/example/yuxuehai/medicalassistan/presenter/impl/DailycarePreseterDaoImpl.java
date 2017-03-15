package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.view.DailycareView;

/**
 * Created by yuxuehai on 17-3-15.
 */

public class DailycarePreseterDaoImpl extends BasePresenter {

    private DailycareView mDailycareView;

    public DailycarePreseterDaoImpl(Context context, DailycareView view) {
        super(context);
        mDailycareView = view;
    }


}
