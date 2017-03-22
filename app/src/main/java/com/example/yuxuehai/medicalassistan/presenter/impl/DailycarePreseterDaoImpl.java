package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.DailycarePreseterDao;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.DailycareView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by yuxuehai on 17-3-15.
 */

public class DailycarePreseterDaoImpl extends BasePresenter implements DailycarePreseterDao {

    private DailycareView mDailycareView;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public DailycarePreseterDaoImpl(Context context, DailycareView view) {
        super(context);
        mDailycareView = view;
    }


    public List<Event> getDataFromServer() {

        mDailycareView.showPrograss();

        mDataModelDao.getEventList(new FindListener<Event>() {

            @Override
            public void done(List<Event> list, BmobException e) {
                if(e == null){
                    mDailycareView.setList(list);
                    mDailycareView.hidePrograss();
                }else {
                    ToastUtil.showShort(getContext(), "查询数据失败");
                }
            }
        });

        return null;
    }
}
