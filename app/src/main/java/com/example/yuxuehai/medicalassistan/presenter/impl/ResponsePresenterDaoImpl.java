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
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yuxuehai on 17-3-21.
 */

public class ResponsePresenterDaoImpl extends BasePresenter<DailyEventView> implements ResponsePresenterDao {

    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public ResponsePresenterDaoImpl(Context context) {
        super(context);
    }

    @Override
    public synchronized void saveEvent() {

        Event event = getView().getEvent();
        mDataModelDao.saveEvent(event, new SaveListener<String>() {

            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastUtil.showShort(getContext(), "添加成功");
                    getView().callBackResult();
                    getView().finishActivity();
                } else {
                    ToastUtil.showShort(getContext(), "添加失败");
                }
            }
        });

    }

    @Override
    public synchronized void UpdateEvent() {

        Event event = getView().getEvent();
        String id = getView().getId();
        mDataModelDao.updateEvent(event, id, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.showShort(getContext(), "修改成功");
                    getView().callBackResult();
                    getView().finishActivity();
                } else {
                    ToastUtil.showShort(getContext(), "修改失败");
                }
            }
        });

    }



    @Override
    public synchronized boolean isEmpty(String name, String location, String object) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(location) && !TextUtils.isEmpty(object)) {
            return true;
        }

        return false;
    }




}
