package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.ResponsePresenterDao;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.DailyEventView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by yuxuehai on 17-3-21.
 */

public class ResponsePresenterDaoImpl extends BasePresenter implements ResponsePresenterDao {

    private DailyEventView mDailyEventView;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public ResponsePresenterDaoImpl(Context context, DailyEventView view) {
        super(context);
        this.mDailyEventView = view;
    }

    @Override
    public void saveEvent() {

        Event event = mDailyEventView.getEvent();
        mDataModelDao.saveEvent(event, new SaveListener<String>() {

            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastUtil.showShort(getContext(), "添加成功");
                    mDailyEventView.callBackResult();
                    mDailyEventView.finishActivity();
                } else {
                    ToastUtil.showShort(getContext(), "添加失败");
                }
            }
        });

    }

    @Override
    public boolean isEmpty(String name, String location, String object) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(location) && !TextUtils.isEmpty(object)) {
            return true;
        }

        return false;
    }




}
