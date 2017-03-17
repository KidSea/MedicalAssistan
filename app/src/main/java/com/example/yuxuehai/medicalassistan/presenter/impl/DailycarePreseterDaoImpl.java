package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.presenter.DailycarePreseterDao;
import com.example.yuxuehai.medicalassistan.view.DailycareView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxuehai on 17-3-15.
 */

public class DailycarePreseterDaoImpl extends BasePresenter implements DailycarePreseterDao {

    private DailycareView mDailycareView;
    private ArrayList<Integer> datas;

    public DailycarePreseterDaoImpl(Context context, DailycareView view) {
        super(context);
        mDailycareView = view;
    }


    public List<Integer> getDataFromServer() {

        mDailycareView.showPrograss();

        datas = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            datas.add(i);
        }


        return datas;
    }
}
